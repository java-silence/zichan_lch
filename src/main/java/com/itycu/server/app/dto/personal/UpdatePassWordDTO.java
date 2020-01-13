package com.itycu.server.app.dto.personal;


import lombok.Data;

@Data
public class UpdatePassWordDTO {


    /**
     * 用户名称
     */
    private String username;
    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;

}
