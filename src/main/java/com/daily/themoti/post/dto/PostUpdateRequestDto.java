package com.daily.themoti.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {

    @NotBlank(message = "게시글 제목을 입력하시오.")
    private String title;

    @NotBlank(message = "게시글 본문을 입력하시오.")
    private String content;

    public PostUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
