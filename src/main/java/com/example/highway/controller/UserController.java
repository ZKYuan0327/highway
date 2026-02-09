package com.example.highway.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.highway.common.Result;
import com.example.highway.form.LoginForm;
import com.example.highway.pojo.User;
import com.example.highway.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import com.example.highway.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;

@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<Object, User> redisTemplate;

    @PostMapping("/signup")
    public Result<String> signUp(@RequestBody User user){
        log.info("11111");
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhone, user.getPhone());
        User one = userService.getOne(queryWrapper);

        if (one == null){
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            userService.save(user);
            return Result.success("用户注册成功");
        }else {
            return Result.error("该手机号码已被注册");
        }
    }

    @PostMapping("/login")
    public Result<LoginForm> login(HttpServletRequest request, @RequestBody User user){
        log.info(user.toString());

        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhone, user.getPhone());
        User one = userService.getOne(queryWrapper);

        if (one == null || !one.getPassword().equals(password)){
            return Result.error("登陆失败");
        }else {
            String jwt = JwtUtil.generateJwtToken(one);
            redisTemplate.opsForValue().set(jwt, one, Duration.ofMinutes(120L));
            return Result.success(new LoginForm(one, jwt));
        }

    }

    @PostMapping("/update")
    public Result<String> update(HttpServletRequest request, @RequestBody User user){
        String token = request.getHeader("Authorization");
        User user1 = redisTemplate.opsForValue().get(token);
        log.info(user1.toString());
        user.setId(user1.getId());
        userService.updateById(user);
        return Result.success("修改成功");
    }

}
