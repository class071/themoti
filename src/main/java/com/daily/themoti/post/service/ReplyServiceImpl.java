package com.daily.themoti.post.service;

import com.daily.themoti.global.exception.NoSuchPostExist;
import com.daily.themoti.global.exception.NoSuchReplyExist;
import com.daily.themoti.post.domain.Reply;
import com.daily.themoti.post.dto.ReplyCreateRequestDto;
import com.daily.themoti.post.dto.ReplyResponseDto;
import com.daily.themoti.post.repository.PostRepository;
import com.daily.themoti.post.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService {

    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    @Override
    public ReplyResponseDto create(ReplyCreateRequestDto replyCreateRequestDto) {
        postRepository.findById(replyCreateRequestDto.getPostId())
                .orElseThrow(() -> new NoSuchPostExist());
        Reply newReply = replyRepository.save(replyCreateRequestDto.toEntity());
        return new ReplyResponseDto(newReply);
    }

    @Transactional
    @Override
    public void delete(Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new NoSuchReplyExist());
        replyRepository.delete(reply);
    }

    @Override
    public List<ReplyResponseDto> readAll(Long postId) {
        List<Reply> replies = replyRepository.findAllByPostId(postId);
        List<ReplyResponseDto> replyResponseDtos = replies.stream().map(reply -> new ReplyResponseDto(reply)).collect(Collectors.toList());
        return replyResponseDtos;
    }


}
