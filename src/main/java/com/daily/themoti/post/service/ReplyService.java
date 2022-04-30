package com.daily.themoti.post.service;

import com.daily.themoti.post.dto.ReplyCreateRequestDto;
import com.daily.themoti.post.dto.ReplyResponseDto;

import java.util.List;

public interface ReplyService {

    ReplyResponseDto create(ReplyCreateRequestDto replyCreateRequestDto);

    void delete(Long replyId);

    List<ReplyResponseDto> readAll(Long postId);
}
