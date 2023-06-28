package com.fastproject.common.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.fastproject.satoken.SaTokenUtil;
import java.time.LocalDateTime;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author fastProject
 * @date 2023/5/14 18:24
 */
@Component
public class DateMetaObjectHandler implements MetaObjectHandler {

  @Override
  public void insertFill(MetaObject metaObject) {
    this.strictInsertFill(metaObject, "createAt", LocalDateTime::now, LocalDateTime.class);
    this.strictInsertFill(metaObject, "updateAt", LocalDateTime::now, LocalDateTime.class);
    this.strictInsertFill(metaObject, "createBy", SaTokenUtil::getUserId, Long.class);
    this.strictInsertFill(metaObject, "updateBy", SaTokenUtil::getUserId, Long.class);
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    this.strictUpdateFill(metaObject, "updateAt", LocalDateTime::now, LocalDateTime.class);
    this.strictUpdateFill(metaObject, "updateBy", SaTokenUtil::getUserId, Long.class);
  }
}
