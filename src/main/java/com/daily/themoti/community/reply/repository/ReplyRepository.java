package com.daily.themoti.community.reply.repository;

import com.daily.themoti.community.reply.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findAllByPostId(Long postId);
}
