package com.daily.themoti.community.reply.dto;

import com.daily.themoti.community.reply.domain.Reply;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReplyResponseDto {

    private Long replyId;

    private String replyContent;

    private Long postId;

    private Long userId;

    private String nickname;

    private String thumbnailURL;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public ReplyResponseDto(Reply reply) {
        this.replyId = reply.getReplyId();
        this.replyContent = reply.getReplyContent();
        this.postId = reply.getPostId();
        this.userId = reply.getUserId();
        this.nickname = reply.getNickname();
        this.thumbnailURL = reply.getThumbnailURL();
        this.createdDate = reply.getCreatedDate();
        this.modifiedDate = reply.getModifiedDate();
    }
}
