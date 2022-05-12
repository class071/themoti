package com.daily.themoti.community.dto;

import com.daily.themoti.community.domain.Reply;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReplyResponseDto {

    private String replyContent;

    private Long postId;

    private Long userId;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public ReplyResponseDto(Reply reply) {
        this.replyContent = reply.getReplyContent();
        this.postId = reply.getPostId();
        this.userId = reply.getUserId();
        this.createdDate = reply.getCreatedDate();
        this.modifiedDate = reply.getModifiedDate();
    }
}