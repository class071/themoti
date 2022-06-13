package com.daily.themoti.user.dto;

import com.daily.themoti.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    Long id;
    String email;
    String username;
    String profileImageURL;
    String thumbnailURL;

    public UserResponseDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.profileImageURL = user.getProfileImageURL();
        this.thumbnailURL = user.getThumbnailURL();
    }
}
