package com.daily.themoti.user.controller;

import com.daily.themoti.global.api.ApiResponse;
import com.daily.themoti.user.dto.LoginResponse;
import com.daily.themoti.user.dto.RefreshTokenResponse;
import com.daily.themoti.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login/{token}")
    public ApiResponse<LoginResponse> login(@PathVariable String token){
        return ApiResponse.success(HttpStatus.OK, userService.loginWithAccessToken(token));
    }

    @GetMapping("/user/kakao/oauth")
    public String getCode(@RequestParam String code){
        return userService.getAccessToken(code);
    }

    @PostMapping("/refresh")
    public ApiResponse<RefreshTokenResponse> refreshToken(@RequestHeader(value = "Authrization") String refreshToken){
        return ApiResponse.success(HttpStatus.OK, userService.refreshToken(refreshToken));
    }
}
