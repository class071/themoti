package com.daily.themoti.post.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(nullable = false)
    private Long userId;

    @Column(length = 500, nullable = false)
    private String content;

    @Builder
    public Post(String title, long userId, String content) {
        this.title = title;
        this.userId = userId;
        this.content = content;
    }
}
