package com.daily.themoti.community.domain;

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

    @Builder
    public Reply(String replyContent, Long postId, Long userId) {
        this.replyContent = replyContent;
        this.postId = postId;
        this.userId = userId;
    }
}
