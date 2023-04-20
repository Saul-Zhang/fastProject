package com.fastproject.service;

import cn.hutool.core.util.StrUtil;
import com.fastproject.common.base.BaseService;
import com.fastproject.common.utils.ConvertUtil;
import com.fastproject.mapper.SysDepartmentMapper;
import com.fastproject.model.auto.Department;
import com.fastproject.model.auto.SysDepartmentExample;
import com.fastproject.model.custom.Tablepar;
import com.fastproject.util.StringUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

/**
 * 部门表 SysDepartmentService
 * 
 * @Title: SysDepartmentService.java
 * @Package com.fc.v2.service
 * @author fuce_自动生成
 * @email 115889198@qq.com
 * @date 2020-04-17 13:12:58
 **/
@Service
public class SysDepartmentService implements BaseService<Department, SysDepartmentExample> {
	@Autowired
	private SysDepartmentMapper sysDepartmentMapper;

	/**
	 * 分页查询
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Department> list(Tablepar tablepar, String name) {
		SysDepartmentExample testExample = new SysDepartmentExample();
		testExample.setOrderByClause("id ASC");
		if (name != null && !"".equals(name)) {
			testExample.createCriteria().andDeptNameLike("%" + name + "%");
		}
		if (StrUtil.isNotEmpty(tablepar.getOrderByColumn())) {
			testExample.setOrderByClause(
					StringUtils.toUnderScoreCase(tablepar.getOrderByColumn()) + " " + tablepar.getIsAsc());
		}
		List<Department> list = sysDepartmentMapper.selectByExample(testExample);
		PageInfo<Department> pageInfo = new PageInfo<Department>(list);
		return pageInfo;
	}

	@Override
	public int deleteByPrimaryKey(String ids) {

		Integer[] integers = ConvertUtil.toIntArray(",", ids);
		List<Integer> stringB = Arrays.asList(integers);
		SysDepartmentExample example = new SysDepartmentExample();
		example.createCriteria().andIdIn(stringB);
		return sysDepartmentMapper.deleteByExample(example);

	}

	@Override
	public Department selectByPrimaryKey(String id) {

		Integer id1 = Integer.valueOf(id);
		return sysDepartmentMapper.selectByPrimaryKey(id1);

	}

	@Override
	public int updateByPrimaryKeySelective(Department record) {
		return sysDepartmentMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 添加
	 */
	@Override
	public int insertSelective(Department record) {

		record.setId(null);

		return sysDepartmentMapper.insertSelective(record);
	}

	@Override
	public int updateByExampleSelective(Department record, SysDepartmentExample example) {

		return sysDepartmentMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Department record, SysDepartmentExample example) {

		return sysDepartmentMapper.updateByExample(record, example);
	}

	@Override
	public List<Department> selectByExample(SysDepartmentExample example) {

		return sysDepartmentMapper.selectByExample(example);
	}

	@Override
	public long countByExample(SysDepartmentExample example) {

		return sysDepartmentMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(SysDepartmentExample example) {

		return sysDepartmentMapper.deleteByExample(example);
	}

	/**
	 * 检查name
	 * 
	 * @param department
	 * @return
	 */
	public int checkNameUnique(Department department) {
		SysDepartmentExample example = new SysDepartmentExample();
		example.createCriteria().andDeptNameEqualTo(department.getName());
		List<Department> list = sysDepartmentMapper.selectByExample(example);
		return list.size();
	}

		/**
	 * 修改权限状态展示或者不展示
	 * @param record
	 * @return
	 */
	public int updateVisible(Department record) {
		return sysDepartmentMapper.updateByPrimaryKeySelective(record);
	}
	
}
