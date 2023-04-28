package com.fastproject.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.models.auth.In;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysFile {
    private String id;

    private String fileName;

    private String bucketName;

    private Long fileSize;

    private String fileSuffix;

    private Integer createUserId;

    private String createUserName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private Integer updateUserId;

    private String updateUserName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

//    public SysFile(String id, String fileName, String bucketName, Long fileSize, String fileSuffix, String createUserId, String createUserName, Date createTime, String updateUserId, String updateUserName, Date updateTime) {
//        this.id = id;
//        this.fileName = fileName;
//        this.bucketName = bucketName;
//        this.fileSize = fileSize;
//        this.fileSuffix = fileSuffix;
//        this.createUserId = createUserId;
//        this.createUserName = createUserName;
//        this.createTime = createTime;
//        this.updateUserId = updateUserId;
//        this.updateUserName = updateUserName;
//        this.updateTime = updateTime;
//    }


}