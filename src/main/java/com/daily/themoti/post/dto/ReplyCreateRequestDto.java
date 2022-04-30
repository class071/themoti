package com.daily.themoti.post.dto;

import com.daily.themoti.post.domain.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class ReplyCreateRequestDto {

    @NotBlank(message = "댓글을 입력하시오.")
    private String replyContent;

    @NotNull(message = "게시글 아이디를 입력하시오.")
    @Positive
    private Long postId;

    private Long userId;

    public ReplyCreateRequestDto(String replyContent, Long postId, Long userId) {
        this.replyContent = replyContent;
        this.postId = postId;
        this.userId = userId;
    }

    public Reply toEntity() {
        return Reply.builder()
                .replyContent(replyContent)
                .postId(postId)
                .userId(userId)
                .build();
    }
}
