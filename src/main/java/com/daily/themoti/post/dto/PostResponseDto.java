package com.daily.themoti.post.dto;

import com.daily.themoti.post.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {

    private String title;
    private long userId;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.userId = post.getUserId();
        this.content = post.getContent();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
    }
}
