package com.fastproject.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.fastproject.common.mybatis.QueryWrapperX;
import com.fastproject.model.Customer;
import com.fastproject.model.Template;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author fastProject
 * @date 2023/6/18 22:55
 */
public interface CustomerMapper extends BaseMapper<Customer> {

    @Select("<script>" +
        "SELECT "
        + "  dc.*   "
        + "from   "
        + "  def_customer dc ,   "
        + "  def_template dt   "
        + "WHERE   "
        + "  dc.field_id = dt.id AND dc.customer_id IN "
        + "<foreach item='id' index='index' collection='ids' open='(' separator=',' close=')'>"
        + "#{id}"
        + "</foreach>"
        + "order by   "
        + "  dc.customer_id ,   "
        + "  dt.order_num "
        + "</script>")
    List<Customer> selectByIdsOrderByOrderNum(@Param("ids") List<Long> ids);

    @Select("<script>" +
        "SELECT "
        + "  dc.*   "
        + "from   "
        + "  def_customer dc ,   "
        + "  def_template dt   "
        + "WHERE   "
        + "  dc.field_id = dt.id "
        + "order by   "
        + "  dc.customer_id ,   "
        + "  dt.order_num "
        + "</script>")
    List<Customer> selectOrderByOrderNum();
}
