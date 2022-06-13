package com.daily.themoti.user.domain;

import com.daily.themoti.user.constant.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    private String profileImageURL;

    @Column
    private String thumbnailURL;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Builder
    public User(String username, String email, String profileImageURL, String thumbnailURL, UserRole role){
        this.username = username;
        this.email = email;
        this.profileImageURL = profileImageURL;
        this.thumbnailURL = thumbnailURL;
        this.role = role;
    }
}
