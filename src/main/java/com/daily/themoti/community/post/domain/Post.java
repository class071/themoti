package com.daily.themoti.community.post.domain;

import com.daily.themoti.community.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(length = 500, nullable = false)
    private String content;

    @Column(nullable = false)
    private Long userId;

    private String nickname;

    private String thumbnailURL;

    @Builder
    public Post(String content, Long userId, String nickname, String thumbnailURL) {
        this.content = content;
        this.userId = userId;
        this.nickname = nickname;
        this.thumbnailURL = thumbnailURL;
    }

    public void update(String content) {
        this.content = content;
    }

    public void setUserProfile(String nickname, String thumbnailURL) {
        this.nickname = nickname;
        this.thumbnailURL = thumbnailURL;
    }
}
