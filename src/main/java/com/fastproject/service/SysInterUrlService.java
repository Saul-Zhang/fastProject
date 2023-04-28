package com.fastproject.service;

import com.fastproject.common.base.BaseService;
import com.fastproject.common.utils.ConvertUtil;
import com.fastproject.mapper.SysInterUrlMapper;
import com.fastproject.model.SysInterUrl;
import com.fastproject.model.auto.SysInterUrlExample;
import com.fastproject.model.custom.Tablepar;
import com.fastproject.util.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 拦截url表 SysInterUrlService
 * @Title: SysInterUrlService.java
 * @Package com.fc.v2.service
 * @author fuce_自动生成
 * @email 115889198@qq.com
 * @date 2020-01-05 01:48:22
 **/
@Service
public class SysInterUrlService implements BaseService<SysInterUrl, SysInterUrlExample>{
	@Autowired
	private SysInterUrlMapper sysInterUrlMapper;
	
      	   	      	      	      	      	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<SysInterUrl> list(Tablepar tablepar,String name){
	        SysInterUrlExample testExample=new SysInterUrlExample();
	        testExample.setOrderByClause("id ASC");
	        if(name!=null&&!"".equals(name)){
	        	testExample.createCriteria().andInterNameLike("%"+name+"%");
	        }

	        PageHelper.startPage(tablepar.getPage(), tablepar.getLimit());
	        List<SysInterUrl> list= sysInterUrlMapper.selectByExample(testExample);
	        PageInfo<SysInterUrl> pageInfo = new PageInfo<SysInterUrl>(list);
	        return  pageInfo;
	 }

	@Override
	public int deleteByPrimaryKey(String ids) {
				
			List<String> lista=ConvertUtil.toListStrArray(ids);
			SysInterUrlExample example=new SysInterUrlExample();
			example.createCriteria().andIdIn(lista);
			return sysInterUrlMapper.deleteByExample(example);
			
				
	}
	
	
	@Override
	public SysInterUrl selectByPrimaryKey(String id) {
				
			return sysInterUrlMapper.selectByPrimaryKey(id);
				
	}

	
	@Override
	public int updateByPrimaryKeySelective(SysInterUrl record) {
		return sysInterUrlMapper.updateByPrimaryKeySelective(record);
	}
	
	
	/**
	 * 添加
	 */
	@Override
	public int insertSelective(SysInterUrl record) {
				
		//添加雪花主键id
		record.setId(SnowflakeIdWorker.getUUID());
			
				
		return sysInterUrlMapper.insertSelective(record);
	}
	
	
	@Override
	public int updateByExampleSelective(SysInterUrl record, SysInterUrlExample example) {
		
		return sysInterUrlMapper.updateByExampleSelective(record, example);
	}

	
	@Override
	public int updateByExample(SysInterUrl record, SysInterUrlExample example) {
		
		return sysInterUrlMapper.updateByExample(record, example);
	}

	@Override
	public List<SysInterUrl> selectByExample(SysInterUrlExample example) {
		
		return sysInterUrlMapper.selectByExample(example);
	}

	
	@Override
	public long countByExample(SysInterUrlExample example) {
		
		return sysInterUrlMapper.countByExample(example);
	}

	
	@Override
	public int deleteByExample(SysInterUrlExample example) {
		
		return sysInterUrlMapper.deleteByExample(example);
	}
	
	/**
	 * 检查name
	 * @param sysInterUrl
	 * @return
	 */
	public int checkNameUnique(SysInterUrl sysInterUrl){
		SysInterUrlExample example=new SysInterUrlExample();
		example.createCriteria().andInterNameEqualTo(sysInterUrl.getInterName());
		List<SysInterUrl> list=sysInterUrlMapper.selectByExample(example);
		return list.size();
	}


}
