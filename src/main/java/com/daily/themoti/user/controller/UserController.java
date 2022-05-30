package com.daily.themoti.user.controller;

import com.daily.themoti.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login/{token}")
    public void login(@PathVariable String token){
        userService.loginWithAccessToken(token);
    }

    @GetMapping("/user/kakao/oauth")
    public String getCode(@RequestParam String code){
        return userService.getAccessToken(code);
    }
}
