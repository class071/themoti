package com.daily.themoti.community.reply.controller;

import com.daily.themoti.community.reply.dto.ReplyCreateRequestDto;
import com.daily.themoti.community.reply.dto.ReplyResponseDto;
import com.daily.themoti.community.reply.service.ReplyService;
import com.daily.themoti.global.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/api/reply")
    public ApiResponse<ReplyResponseDto> create(@Valid @RequestBody ReplyCreateRequestDto replyCreateRequestDto) {
        ReplyResponseDto replyResponseDto = replyService.create(replyCreateRequestDto);
        return ApiResponse.success(HttpStatus.CREATED, replyResponseDto);
    }

    @DeleteMapping("/api/reply/{replyId}")
    public ApiResponse<ReplyResponseDto> delete(@PathVariable Long replyId) {
        replyService.delete(replyId);
        return ApiResponse.success(HttpStatus.OK, replyId);
    }

    @GetMapping("/api/reply/{postId}")
    public ApiResponse<List<ReplyResponseDto>> readAll(@PathVariable Long postId) {
        List<ReplyResponseDto> replyResponseDtos = replyService.readAll(postId);
        return ApiResponse.success(HttpStatus.OK, replyResponseDtos);
    }
}
