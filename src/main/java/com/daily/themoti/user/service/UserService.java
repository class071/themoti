package com.daily.themoti.user.service;

import com.daily.themoti.user.dto.LoginResponse;
import com.daily.themoti.user.dto.RefreshTokenResponse;
import com.daily.themoti.user.dto.UserResponseDto;

public interface UserService {

    LoginResponse loginWithAccessToken(String token);

    String getAccessToken(String code);

    RefreshTokenResponse refreshToken(String rToken);

    UserResponseDto getUserInfo();
}
