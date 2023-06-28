package com.fastproject.common.mybatis;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fastproject.model.AuditContent;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.util.CollectionUtils;

/**
 * @author fastProject
 * @date 2023/6/25 23:57
 */
//数据库类型
@MappedJdbcTypes(JdbcType.VARCHAR)
////java数据类型
@MappedTypes({List.class})
public class AuditContentListHandler extends BaseTypeHandler<List<AuditContent>> {

//  @Override
//  protected List<AuditContent> parse(String json) {
//    return JSONUtil.toList(JSONUtil.parseArray(json), AuditContent.class);
//  }
//
//  @Override
//  protected String toJson(List<AuditContent> list) {
//    return JSONUtil.toJsonStr(list);
//  }

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, List<AuditContent> parameter,
      JdbcType jdbcType) throws SQLException {
    String content = CollectionUtils.isEmpty(parameter) ? null : JSONUtil.toJsonStr(parameter);
    ps.setString(i, content);
  }

  @Override
  public List<AuditContent> getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return this.getListByJsonArrayString(rs.getString(columnName));
  }

  @Override
  public List<AuditContent> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return this.getListByJsonArrayString(rs.getString(columnIndex));
  }

  @Override
  public List<AuditContent> getNullableResult(CallableStatement cs, int columnIndex)
      throws SQLException {
    return this.getListByJsonArrayString(cs.getString(columnIndex));
  }

  private List<AuditContent> getListByJsonArrayString(String content) {
    return StrUtil.isEmpty(content) ? new ArrayList<>()
        : JSONUtil.toList(JSONUtil.parseArray(content), AuditContent.class);
  }
}
