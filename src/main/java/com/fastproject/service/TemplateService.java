package com.fastproject.service;

import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.mapper.TemplateMapper;
import com.fastproject.model.Template;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.TemplateResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author fastProject
 * @date 2023/6/18 22:55
 */
@Service
@RequiredArgsConstructor
public class TemplateService {

  private final TemplateMapper templateMapper;


  public List<TemplateResponse> list() {
    return getTemplateList().stream()
        .map(TemplateResponse::fromTemplate).collect(Collectors.toList());
  }

  //  @Cacheable("templates")
  public List<Template> getTemplateList() {
    return templateMapper.selectList(new LambdaQueryWrapperX<Template>().orderByAsc(Template::getOrderNum));
  }

  public AjaxResult add(Template template) {
    templateMapper.insert(template);
    return AjaxResult.success();
  }

  public Map<Long, Template> getTemplateMap() {
    return getTemplateList().stream()
        .collect(Collectors.toMap(Template::getId, t -> t));
  }

  public Map<String, Template> getTemplateNameMap() {
    return getTemplateList().stream()
        .collect(Collectors.toMap(Template::getFieldName, t -> t));
  }

  public Template selectById(Long id) {
    return templateMapper.selectById(id);
  }

  public AjaxResult updateById(Template template) {
    templateMapper.updateById(template);
    return AjaxResult.success();
  }

  public AjaxResult deleteByIds(List<Long> ids) {
    templateMapper.deleteBatchIds(ids);
    return AjaxResult.success();
  }
}
