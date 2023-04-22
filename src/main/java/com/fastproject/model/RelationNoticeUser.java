package com.fastproject.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.lang.Integer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("rel_notice_user")
public class RelationNoticeUser implements Serializable {

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