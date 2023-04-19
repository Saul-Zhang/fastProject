package com.fastproject.model.custom.autocode;

import java.util.List;
import lombok.Data;

/**
 * 配置文件model
 */
@Data
public class AutoConfigModel {
	/**表名称**/
	private String tableName;
	/**表描述**/
	private String tableComment;
	/**作者**/
	private String author;
	/** 父菜单**/
	private String pid;
	/**自定义路径**/
	private String parentPath;
	
	private List<BeanColumn> beanColumns;
	
}
