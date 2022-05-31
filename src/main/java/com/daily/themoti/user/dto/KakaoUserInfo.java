package com.daily.themoti.user.dto;

import com.daily.themoti.user.constant.UserRole;
import com.daily.themoti.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KakaoUserInfo {

    String email;
    String nickname;

    public User toEntity(){
        return User.builder()
                .email(email)
                .username(nickname)
                .role(UserRole.USER)
                .build();
    }
}
