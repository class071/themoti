package com.daily.themoti.community.reply.service;

import com.daily.themoti.community.exception.NoSuchPostExist;
import com.daily.themoti.community.exception.NoSuchReplyExist;
import com.daily.themoti.community.post.repository.PostRepository;
import com.daily.themoti.community.reply.domain.Reply;
import com.daily.themoti.community.reply.dto.ReplyCreateRequestDto;
import com.daily.themoti.community.reply.dto.ReplyResponseDto;
import com.daily.themoti.community.reply.repository.ReplyRepository;
import com.daily.themoti.user.domain.User;
import com.daily.themoti.user.exception.UserNotExistException;
import com.daily.themoti.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService {

    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public ReplyResponseDto create(ReplyCreateRequestDto replyCreateRequestDto) {
        postRepository.findById(replyCreateRequestDto.getPostId())
                .orElseThrow(() -> new NoSuchPostExist());
        Reply newReply = replyRepository.save(replyCreateRequestDto.toEntity());
        User user = userRepository.findById(replyCreateRequestDto.getUserId())
                .orElseThrow(() -> new UserNotExistException());
        newReply.setUserProfile(user.getUsername(), user.getThumbnailURL());
        return new ReplyResponseDto(newReply);
    }

    @Transactional
    @PreAuthorize("@replyGuard.check(#replyId)")
    @Override
    public void delete(Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new NoSuchReplyExist());
        replyRepository.delete(reply);
    }

    @Override
    public List<ReplyResponseDto> readAll(Long postId) {
        List<Reply> replies = replyRepository.findAllByPostId(postId);
        List<ReplyResponseDto> replyResponseDtos = replies.stream()
                .map(reply -> new ReplyResponseDto(reply))
                .collect(Collectors.toList());
        return replyResponseDtos;
    }
}
