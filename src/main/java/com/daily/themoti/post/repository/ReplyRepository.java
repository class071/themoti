package com.daily.themoti.post.repository;

import com.daily.themoti.post.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findAllByPostId(Long postId);
}
