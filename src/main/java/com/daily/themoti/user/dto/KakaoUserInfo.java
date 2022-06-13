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
    String profileImageURL;
    String thumbnailURL;

    public User toEntity(){
        return User.builder()
                .email(email)
                .username(nickname)
                .profileImageURL(profileImageURL)
                .thumbnailURL(thumbnailURL)
                .role(UserRole.USER)
                .build();
    }
}
