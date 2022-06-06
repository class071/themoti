package com.daily.themoti.jwt.config.guard;

import com.daily.themoti.community.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostGuard extends Guard {

    private final PostRepository postRepository;

    @Override
    protected boolean isResourceOwner(Long id) {
        return postRepository.findById(id)
                .map(post -> post.getUserId())
                .filter(userId -> userId.equals(AuthenticationHelper.extractUserId()))
                .isPresent();
    }
}
