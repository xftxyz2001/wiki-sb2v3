package com.java.wiki.req;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserResetPasswordReq {
    private Long id;

    @NotBlank(message = "【密码】不能为空")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-z]+$)[0-9A-Za-z]{6,32}$",
            message = "【密码】至少包含 数字和英文，长度6-32")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserResetPasswordReq{" +
                "id=" + id +
                ", password='" + password + '\'' +
                '}';
    }
}