package com.daily.themoti.community.reply.service;

import com.daily.themoti.community.reply.dto.ReplyCreateRequestDto;
import com.daily.themoti.community.reply.dto.ReplyResponseDto;

import java.util.List;

public interface ReplyService {

    ReplyResponseDto create(ReplyCreateRequestDto replyCreateRequestDto);

    void delete(Long replyId);

    List<ReplyResponseDto> readAll(Long postId);
}
