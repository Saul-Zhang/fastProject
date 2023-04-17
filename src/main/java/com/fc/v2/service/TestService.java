package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.hutool.core.util.StrUtil;
import com.fc.v2.common.base.BaseService;
import com.fc.v2.common.support.ConvertUtil;
import com.fc.v2.mapper.auto.TestMapper;
import com.fc.v2.model.auto.Test;
import com.fc.v2.model.auto.TestExample;
import com.fc.v2.model.custom.Tablepar;
import com.fc.v2.util.SnowflakeIdWorker;
import com.fc.v2.util.StringUtils;

/**
 * 测试表 TestService
 * @Title: TestService.java 
 * @Package com.fc.v2.service 
 * @author fuce_自动生成
 * @email ${email}
 * @date 2023-04-05 17:13:51  
 **/
@Service
public class TestService implements BaseService<Test, TestExample>{
	@Autowired
	private TestMapper testMapper;
	
      	   	      	      	      	      	      	      	      	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<Test> list(Tablepar tablepar,Test test){
		 QueryWrapper<Test> queryWrapper = new QueryWrapper<>();

		 List<Test> tests = testMapper.selectList(null);

		 TestExample testExample=new TestExample();
			//搜索
			if(StrUtil.isNotEmpty(tablepar.getSearchText())) {
	        	testExample.createCriteria().andLikeQuery2(tablepar.getSearchText());
	        }else {//大搜索
	        	testExample.createCriteria().andLikeQuery(test);
	        }
			//表格排序
			//if(StrUtil.isNotEmpty(tablepar.getOrderByColumn())) {
	        //	testExample.setOrderByClause(StringUtils.toUnderScoreCase(tablepar.getOrderByColumn()) +" "+tablepar.getIsAsc());
	        //}else{
	        //	testExample.setOrderByClause("id ASC");
	        //}
	        PageHelper.startPage(tablepar.getPage(), tablepar.getLimit());
	        List<Test> list= testMapper.selectByExample(testExample);
	        PageInfo<Test> pageInfo = new PageInfo<Test>(list);
	        return  pageInfo;
	 }

	@Override
	public int deleteByPrimaryKey(String ids) {
				
			List<String> lista=ConvertUtil.toListStrArray(ids);
			TestExample example=new TestExample();
			example.createCriteria().andIdIn(lista);
			return testMapper.deleteByExample(example);
			
				
	}
	
	
	@Override
	public Test selectByPrimaryKey(String id) {
				
			return testMapper.selectByPrimaryKey(id);
				
	}

	
	@Override
	public int updateByPrimaryKeySelective(Test record) {
		return testMapper.updateByPrimaryKeySelective(record);
	}
	
	
	/**
	 * 添加
	 */
	@Override
	public int insertSelective(Test record) {
				
		//添加雪花主键id
		record.setId(SnowflakeIdWorker.getUUID());
			
				
		return testMapper.insertSelective(record);
	}
	
	
	@Override
	public int updateByExampleSelective(Test record, TestExample example) {
		
		return testMapper.updateByExampleSelective(record, example);
	}

	
	@Override
	public int updateByExample(Test record, TestExample example) {
		
		return testMapper.updateByExample(record, example);
	}

	@Override
	public List<Test> selectByExample(TestExample example) {
		
		return testMapper.selectByExample(example);
	}

	
	@Override
	public long countByExample(TestExample example) {
		
		return testMapper.countByExample(example);
	}

	
	@Override
	public int deleteByExample(TestExample example) {
		
		return testMapper.deleteByExample(example);
	}
	
	/**
	 * 修改权限状态展示或者不展示
	 * @param test
	 * @return
	 */
	public int updateVisible(Test test) {
		return testMapper.updateByPrimaryKeySelective(test);
	}


}
