package com.daily.themoti.jwt.config.guard;

import com.daily.themoti.community.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReplyGuard extends Guard {

    private final ReplyRepository replyRepository;

    @Override
    protected boolean isResourceOwner(Long id) {
        return replyRepository.findById(id)
                .map(reply -> reply.getUserId())
                .filter(userId -> userId.equals(AuthenticationHelper.extractUserId()))
                .isPresent();
    }
}
