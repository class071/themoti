package com.daily.themoti.community.post.repository;

import com.daily.themoti.community.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
