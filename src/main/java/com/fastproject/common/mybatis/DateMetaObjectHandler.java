package com.fastproject.common.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import java.util.Date;
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
    this.setFieldValByName("create_time",new Date(),metaObject);
    this.setFieldValByName("update_time",new Date(),metaObject);
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    this.setFieldValByName("update_time",new Date(),metaObject);
  }
}
