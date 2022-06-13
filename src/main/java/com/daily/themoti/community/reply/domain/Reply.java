package com.daily.themoti.community.reply.domain;

import com.daily.themoti.community.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Reply extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @Column(length = 100, nullable = false)
    private String replyContent;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long userId;

    private String nickname;

    private String thumbnailURL;

    @Builder
    public Reply(String replyContent, Long postId, Long userId, String nickname, String thumbnailURL) {
        this.replyContent = replyContent;
        this.postId = postId;
        this.userId = userId;
        this.nickname = nickname;
        this.thumbnailURL = thumbnailURL;
    }

    public void setUserProfile(String nickname, String thumbnailURL) {
        this.nickname = nickname;
        this.thumbnailURL = thumbnailURL;
    }
}
