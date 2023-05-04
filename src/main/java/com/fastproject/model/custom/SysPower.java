package com.fastproject.model.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysPower{

    /**
     * 编号
     * */
    private Long powerId;

    /**
     * 权限名称
     * */
    private String powerName;

    /**
     * 类型
     * */
    private Integer powerType;

    /**
     * 标识
     * */
    private String powerCode;

    /**
     * 路径
     * */
    private String powerUrl;

    /**
     * 打开方式
     * */
    private Integer openType;

    /**
     * 父级编号
     * */
    private Long parentId;

    /**
     * 图标
     * */
    private String icon;

    /**
     * 排序
     * */
    private Integer sort;

    /**
     * 开启
     * */
    private Integer enable;

    /**
     * 计算列 提供给前端组件
     * */
    private String checkArr = "0";

//    public SysPower() {
//    }
//
//    public SysPower(String powerId, String powerName, Integer powerType, String powerCode, String powerUrl, Integer openType, String parentId, String icon, Integer sort, Integer enable, String checkArr) {
//        this.powerId = powerId;
//        this.powerName = powerName;
//        this.powerType = powerType;
//        this.powerCode = powerCode;
//        this.powerUrl = powerUrl;
//        this.openType = openType;
//        this.parentId = parentId;
//        this.icon = icon;
//        this.sort = sort;
//        this.enable = enable;
//        this.checkArr = checkArr;
//    }
}
