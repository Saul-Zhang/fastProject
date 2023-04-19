package com.fastproject.service;

import com.fastproject.common.base.BaseService;
import com.fastproject.common.support.ConvertUtil;
import com.fastproject.mapper.RelationNoticeUserMapper;
import com.fastproject.model.auto.RelationNoticeUser;
import com.fastproject.model.auto.SysNoticeUserExample;
import com.fastproject.model.custom.Tablepar;
import com.fastproject.util.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公告_用户外键 SysNoticeUserService
 * @Title: SysNoticeUserService.java
 * @Package com.fc.v2.service
 * @author fuce_自动生成
 * @email 115889198@qq.com
 * @date 2019-09-08 02:12:54
 **/
@Service
public class SysNoticeUserService implements BaseService<RelationNoticeUser, SysNoticeUserExample>{
	@Autowired
	private RelationNoticeUserMapper relationNoticeUserMapper;
	   	   	      	      	      	      	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<RelationNoticeUser> list(Tablepar tablepar,String name){
	        SysNoticeUserExample testExample=new SysNoticeUserExample();
	        testExample.setOrderByClause("id ASC");
	        if(name!=null&&!"".equals(name)){
	        	testExample.createCriteria().andUserIdLike("%"+name+"%");
	        }

	        PageHelper.startPage(tablepar.getPage(), tablepar.getLimit());
	        List<RelationNoticeUser> list= relationNoticeUserMapper.selectByExample(testExample);
	        PageInfo<RelationNoticeUser> pageInfo = new PageInfo<RelationNoticeUser>(list);
	        return  pageInfo;
	 }

	@Override
	public int deleteByPrimaryKey(String ids) {
		List<String> lista=ConvertUtil.toListStrArray(ids);
		SysNoticeUserExample example=new SysNoticeUserExample();
		example.createCriteria().andIdIn(lista);
		return relationNoticeUserMapper.deleteByExample(example);
	}
	
	
	@Override
	public RelationNoticeUser selectByPrimaryKey(String id) {
		
		return relationNoticeUserMapper.selectByPrimaryKey(id);
	}

	
	@Override
	public int updateByPrimaryKeySelective(RelationNoticeUser record) {
		return relationNoticeUserMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 添加
	 */
	@Override
	public int insertSelective(RelationNoticeUser record) {
		//添加雪花主键id
		record.setId(SnowflakeIdWorker.getUUID());
		return relationNoticeUserMapper.insertSelective(record);
	}
	
	
	@Override
	public int updateByExampleSelective(RelationNoticeUser record, SysNoticeUserExample example) {
		
		return relationNoticeUserMapper.updateByExampleSelective(record, example);
	}

	
	@Override
	public int updateByExample(RelationNoticeUser record, SysNoticeUserExample example) {
		
		return relationNoticeUserMapper.updateByExample(record, example);
	}

	@Override
	public List<RelationNoticeUser> selectByExample(SysNoticeUserExample example) {
		
		return relationNoticeUserMapper.selectByExample(example);
	}

	
	@Override
	public long countByExample(SysNoticeUserExample example) {
		
		return relationNoticeUserMapper.countByExample(example);
	}

	
	@Override
	public int deleteByExample(SysNoticeUserExample example) {
		
		return relationNoticeUserMapper.deleteByExample(example);
	}
	
	/**
	 * 检查name
	 * @param relationNoticeUser
	 * @return
	 */
	public int checkNameUnique(RelationNoticeUser relationNoticeUser){
		SysNoticeUserExample example=new SysNoticeUserExample();
//		example.createCriteria().andUserIdEqualTo(relationNoticeUser.getUserId());
		List<RelationNoticeUser> list= relationNoticeUserMapper.selectByExample(example);
		return list.size();
	}
	
}
