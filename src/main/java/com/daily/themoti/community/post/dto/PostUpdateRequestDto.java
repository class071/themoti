package com.daily.themoti.community.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {

    @NotBlank(message = "게시글 본문을 입력하시오.")
    @Size(max = 500)
    private String content;

    public PostUpdateRequestDto(String title, String content) {
        this.content = content;
    }
}
