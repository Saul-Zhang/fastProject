package com.fastproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastproject.model.auto.Test;
import com.fastproject.model.auto.TestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 测试表 TestMapper
 * @author fuce_自动生成
 * @email ${email}
 * @date 2023-04-05 17:13:51
 */
public interface TestMapper extends BaseMapper<Test> {
      	   	      	      	      	      	      	      	      
    long countByExample(TestExample example);

    int deleteByExample(TestExample example);
		
    int deleteByPrimaryKey(String id);
		
    int insert(Test record);

    int insertSelective(Test record);

    List<Test> selectByExample(TestExample example);
		
    Test selectByPrimaryKey(String id);
		
    int updateByExampleSelective(@Param("record") Test record, @Param("example") TestExample example);

    int updateByExample(@Param("record") Test record, @Param("example") TestExample example); 
		
    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);
  	  	
}