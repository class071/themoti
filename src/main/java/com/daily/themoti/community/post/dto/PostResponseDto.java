package com.daily.themoti.community.post.dto;

import com.daily.themoti.community.post.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {

    private Long postId;

    private String content;

    private Long userId;

    private String nickname;

    private String thumbnailURL;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public PostResponseDto(Post post) {
        this.postId = post.getPostId();
        this.content = post.getContent();
        this.userId = post.getUserId();
        this.nickname = post.getNickname();
        this.thumbnailURL = post.getThumbnailURL();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
    }
}
