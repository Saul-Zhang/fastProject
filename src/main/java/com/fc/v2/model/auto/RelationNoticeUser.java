package com.fc.v2.model.auto;

import java.io.Serializable;
import java.lang.Integer;
import lombok.Data;

@Data
public class SysNoticeUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
		
	/** 主键 **/
	private String id;
		
	/** 公告id **/
	private String noticeId;
		
	/** 用户id **/
	private String userId;
		
	/** 0未阅读 1 阅读 **/
	private Integer state;

}