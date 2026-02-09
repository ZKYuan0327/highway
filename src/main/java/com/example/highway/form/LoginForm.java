package com.example.highway.form;

import com.example.highway.pojo.User;
import lombok.Data;

@Data
public class LoginForm {
    User user;

    String token;

    public LoginForm(User one, String jwt) {
        this.user = one;
        this.token = jwt;
    }
}
