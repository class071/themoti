package com.daily.themoti.community.post.dto;

import com.daily.themoti.community.post.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class PostCreateRequestDto {

    @NotBlank(message = "게시글 제목을 입력하시오.")
    @Size(max = 50)
    private String title;

    @NotBlank(message = "게시글 본문을 입력하시오.")
    @Size(max = 500)
    private String content;

    private Long userId;

    public PostCreateRequestDto(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .build();
    }
}
