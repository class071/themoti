package com.daily.themoti.user.service;

public interface UserService {

    void loginWithAccessToken(String token);

    String getAccessToken(String code);
}
