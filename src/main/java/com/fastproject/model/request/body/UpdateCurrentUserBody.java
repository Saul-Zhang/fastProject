package com.fastproject.model.request.body;

import com.fastproject.model.User;
import lombok.Data;

/**
 * @author fastProjcet
 * @date 2023/4/22 0:24
 */
@Data
public class UpdateCurrentUserBody {

    private Long id;

    /**
     * 用户账号
     **/
    private String username;

    /**
     * 姓名
     **/
    private String realName;

    private String phone;

    private String email;

    public  void updateUserByRequest(UpdateCurrentUserBody body, User user){
        user.setUsername(body.getUsername());
        user.setRealName(body.getRealName());
        user.setPhone(body.getPhone());
        user.setEmail(body.getEmail());
    }
}
