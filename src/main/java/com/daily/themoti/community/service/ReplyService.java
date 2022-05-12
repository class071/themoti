package com.daily.themoti.community.service;

import com.daily.themoti.community.dto.ReplyCreateRequestDto;
import com.daily.themoti.community.dto.ReplyResponseDto;

import java.util.List;

public interface ReplyService {

    ReplyResponseDto create(ReplyCreateRequestDto replyCreateRequestDto);

    void delete(Long replyId);

    List<ReplyResponseDto> readAll(Long postId);
}