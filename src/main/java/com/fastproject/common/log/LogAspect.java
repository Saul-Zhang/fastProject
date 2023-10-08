package com.fastproject.common.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fastproject.common.annotation.Log;
import com.fastproject.model.OperationLog;
import com.fastproject.model.User;
import com.fastproject.satoken.SaTokenUtil;
import com.fastproject.service.OperationLogService;
import com.fastproject.util.ServletUtils;
import com.fastproject.util.StringUtils;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

@Aspect
@Component
@EnableAsync
@RequiredArgsConstructor
public class LogAspect {

  private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

  /**
   * 计算操作消耗时间
   */
  private static final ThreadLocal<Long> TIME_THREAD_LOCAL = new NamedThreadLocal<Long>(
      "Cost Time");


  private final ObjectMapper objectMapper;

  private final OperationLogService operationLogService;


  /**
   * 处理请求前执行
   */
  @Before(value = "@annotation(com.fastproject.common.annotation.Log)")
  public void boBefore(JoinPoint joinPoint) {
    TIME_THREAD_LOCAL.set(System.currentTimeMillis());
  }


  @Pointcut("@annotation(com.fastproject.common.annotation.Log)")
  public void logPointCut() {
  }

  /**
   * 前置通知 用于拦截操作
   *
   * @param joinPoint 切点
   */
  @AfterReturning(pointcut = "logPointCut()")
  public void doBefore(JoinPoint joinPoint) {
    handleLog(joinPoint, null);
  }

  /**
   * 拦截异常操作
   */
  @AfterThrowing(value = "logPointCut()", throwing = "e")
  public void doAfter(JoinPoint joinPoint, Exception e) {
    handleLog(joinPoint, e);
  }

  @Async
  protected void handleLog(final JoinPoint joinPoint, final Exception e) {
    try {
      // 获得注解
      Log controllerLog = getAnnotationLog(joinPoint);
      if (controllerLog == null) {
        return;
      }

      // 获取当前的用户
      User currentUser = SaTokenUtil.getUser();

      // *========数据库日志=========*//
      OperationLog operationLog = new OperationLog();

      operationLog.setUrl(ServletUtils.getRequest().getRequestURI());
      if (currentUser != null) {
//            	//操作人
        operationLog.setOperator(currentUser.getId());
//                if (StringUtils.isNotNull(currentUser.getDept())
//                        && StringUtils.isNotEmpty(currentUser.getDept().getDeptName()))
//                {
//                    operLog.setDeptName(currentUser.getDept().getDeptName());
//                }
      }

      if (e != null) {
        //错误日志
        operationLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
      }
      // 设置方法名称
      String className = joinPoint.getTarget().getClass().getName();
      String methodName = joinPoint.getSignature().getName();
      operationLog.setMethod(className + "." + methodName + "()");
      operationLog.setCreateAt(new Date());
      // 设置消耗时间
      operationLog.setCostTime(System.currentTimeMillis() - TIME_THREAD_LOCAL.get());
      // 处理设置注解上的参数
//      getControllerMethodDescription(controllerLog, operationLog);
      // 设置标题
      operationLog.setTitle(controllerLog.title());
      setRequestValue(joinPoint, operationLog);
      operationLogService.add(operationLog);
    } catch (Exception exp) {
      // 记录本地异常日志
      log.error("==前置通知异常==");
      log.error("异常信息:{}", exp.getMessage());
      exp.printStackTrace();
    }
  }

  private void setRequestValue(JoinPoint joinPoint, OperationLog operaLog) throws Exception {
    Map<String, String[]> map = ServletUtils.getRequest().getParameterMap();
    if (StringUtils.isNotEmpty(map)) {
      String params = objectMapper.writeValueAsString(map);
      operaLog.setParam(StringUtils.substring(params, 0, 2000));
    } else {
      Object args = joinPoint.getArgs();
      if (StringUtils.isNotNull(args)) {
        String params = argsArrayToString(joinPoint.getArgs());
        operaLog.setParam(StringUtils.substring(params, 0, 2000));
      }
    }
  }

  /**
   * 参数拼装
   */
  private String argsArrayToString(Object[] paramsArray) {
    String params = "";
    if (paramsArray != null && paramsArray.length > 0) {
      for (Object o : paramsArray) {
        if (StringUtils.isNotNull(o) && !isFilterObject(o)) {
          try {
            String jsonObj = objectMapper.writeValueAsString(o);
//            Object jsonObj = JSONObject.toJSONString(o, excludePropertyPreFilter(excludeParamNames));
            params += jsonObj + " ";
          } catch (Exception e) {
          }
        }
      }
    }
    return params.trim();
  }

  /**
   * 判断是否需要过滤的对象。
   *
   * @param o 对象信息。
   * @return 如果是需要过滤的对象，则返回true；否则返回false。
   */
  @SuppressWarnings("rawtypes")
  public boolean isFilterObject(final Object o) {
    Class<?> clazz = o.getClass();
    if (clazz.isArray()) {
      return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
    } else if (Collection.class.isAssignableFrom(clazz)) {
      Collection collection = (Collection) o;
      for (Object value : collection) {
        return value instanceof MultipartFile;
      }
    } else if (Map.class.isAssignableFrom(clazz)) {
      Map map = (Map) o;
      for (Object value : map.entrySet()) {
        Map.Entry entry = (Map.Entry) value;
        return entry.getValue() instanceof MultipartFile;
      }
    }
    return o instanceof MultipartFile || o instanceof HttpServletRequest
        || o instanceof HttpServletResponse
        || o instanceof BindingResult;
  }

  /**
   * 获取注解中对方法的描述信息 用于Controller层注解
   */
  public void getControllerMethodDescription(Log log, OperationLog operLog) throws Exception {
    // 设置action动作
    // operLog.setAction(log.action());
    // 设置标题
    operLog.setTitle(log.title());
    // 设置channel
    //operLog.setChannel(log.channel());
    // 是否需要保存request，参数和值
    if (log.isSaveRequestData()) {
      // 获取参数的信息，传入到数据库中。
      setRequestValue(operLog);
    }
  }

  /**
   * 获取请求的参数，放到log中
   */
  private void setRequestValue(OperationLog operaLog) throws JsonProcessingException {
    Map<String, String[]> map = ServletUtils.getRequest().getParameterMap();
    String params = objectMapper.writeValueAsString(map);
    operaLog.setParam(StringUtils.substring(params, 0, 255));
  }

  /**
   * 是否存在注解，如果存在就获取
   */
  private Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
    Signature signature = joinPoint.getSignature();
    MethodSignature methodSignature = (MethodSignature) signature;
    Method method = methodSignature.getMethod();

    if (method != null) {
      return method.getAnnotation(Log.class);
    }
    return null;
  }
}
