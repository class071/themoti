package com.daily.themoti.community.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {

    @NotBlank(message = "게시글 제목을 입력하시오.")
    @Size(max = 50)
    private String title;

    @NotBlank(message = "게시글 본문을 입력하시오.")
    @Size(max = 500)
    private String content;

    public PostUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
