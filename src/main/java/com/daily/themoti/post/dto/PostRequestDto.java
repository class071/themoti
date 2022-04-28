package com.daily.themoti.post.dto;

import com.daily.themoti.post.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {

    private String title;
    private long userId;
    private String content;

    @Builder
    public PostRequestDto(String title, long userId, String content) {
        this.title = title;
        this.userId = userId;
        this.content = content;
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .userId(userId)
                .content(content)
                .build();
    }
}
