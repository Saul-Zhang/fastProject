package com.fastproject.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastproject.common.base.BaseService;
import com.fastproject.common.utils.ConvertUtil;
import com.fastproject.mapper.TSysEmailMapper;
import com.fastproject.model.auto.TSysEmail;
import com.fastproject.model.auto.TSysEmailExample;
import com.fastproject.model.custom.Tablepar;
import com.fastproject.satoken.SaTokenUtil;
import com.fastproject.util.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 电子邮件Service
* @Title: TSysEmailService.java
* @Package com.fc.v2.service
* @author fuce
* @email 87766867@qq.com
* @date 2019-06-30 00:49:49
 */
@Service
public class TSysEmailService implements BaseService<TSysEmail, TSysEmailExample>{
	@Autowired
	private TSysEmailMapper tSysEmailMapper;
	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<TSysEmail> list(Tablepar tablepar,String name){
	        TSysEmailExample testExample=new TSysEmailExample();
	        testExample.setOrderByClause("id desc");
	        if(name!=null&&!"".equals(name)){
	        	testExample.createCriteria().andContentLike("%"+name+"%");
	        }
	        PageHelper.startPage(tablepar.getPage(), tablepar.getLimit());
	        List<TSysEmail> list= tSysEmailMapper.selectByExample(testExample);
	        PageInfo<TSysEmail> pageInfo = new PageInfo<TSysEmail>(list);
	        return  pageInfo;
	 }

	@Override
	public int deleteByPrimaryKey(String ids) {
		List<String> lista=ConvertUtil.toListStrArray(ids);
		TSysEmailExample example=new TSysEmailExample();
		example.createCriteria().andIdIn(lista);
		return tSysEmailMapper.deleteByExample(example);
	}
	
	
	@Override
	public TSysEmail selectByPrimaryKey(String id) {
		
		return tSysEmailMapper.selectByPrimaryKey(id);
	}

	
	@Override
	public int updateByPrimaryKeySelective(TSysEmail record) {
		return tSysEmailMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 添加
	 */
	@Override
	public int insertSelective(TSysEmail record) {
		//添加雪花主键id
		record.setId(SnowflakeIdWorker.getUUID());
		record.setSendUserId(SaTokenUtil.getUserId());
		record.setSendUserName(SaTokenUtil.getLoginName());
		record.setCreateTime(new Date());
		return tSysEmailMapper.insertSelective(record);
	}
	
	@Override
	public int updateByExampleSelective(TSysEmail record, TSysEmailExample example) {
		
		return tSysEmailMapper.updateByExampleSelective(record, example);
	}

	
	@Override
	public int updateByExample(TSysEmail record, TSysEmailExample example) {
		
		return tSysEmailMapper.updateByExample(record, example);
	}

	@Override
	public List<TSysEmail> selectByExample(TSysEmailExample example) {
		
		return tSysEmailMapper.selectByExample(example);
	}

	
	@Override
	public long countByExample(TSysEmailExample example) {
		
		return tSysEmailMapper.countByExample(example);
	}

	
	@Override
	public int deleteByExample(TSysEmailExample example) {
		
		return tSysEmailMapper.deleteByExample(example);
	}
	
	/**
	 * 检查name
	 * @param TSysEmail
	 * @return
	 */
	public int checkNameUnique(TSysEmail tSysEmail){
		TSysEmailExample example=new TSysEmailExample();
		example.createCriteria().andContentEqualTo(tSysEmail.getContent());
		List<TSysEmail> list=tSysEmailMapper.selectByExample(example);
		return list.size();
	}


}
