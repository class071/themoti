package com.daily.themoti.post.controller;

import com.daily.themoti.global.api.ApiResponse;
import com.daily.themoti.global.constant.ReplySuccessCode;
import com.daily.themoti.post.dto.ReplyCreateRequestDto;
import com.daily.themoti.post.dto.ReplyResponseDto;
import com.daily.themoti.post.service.ReplyService;
import lombok.RequiredArgsConstructor;
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
        ReplySuccessCode code = ReplySuccessCode.REPLY_CREATE_SUCCESS;
        return ApiResponse.success(code.name(), code.getHttpStatus(), code.getMessage(), replyResponseDto);
    }

    @DeleteMapping("/api/reply/{replyId}")
    public ApiResponse<ReplyResponseDto> delete(@PathVariable Long replyId) {
        replyService.delete(replyId);
        ReplySuccessCode code = ReplySuccessCode.REPLY_DELETE_SUCCESS;
        return ApiResponse.success(code.name(), code.getHttpStatus(), code.getMessage(), replyId);
    }

    @GetMapping("/api/reply/{postId}")
    public ApiResponse<List<ReplyResponseDto>> readAll(@PathVariable Long postId) {
        List<ReplyResponseDto> replyResponseDtos = replyService.readAll(postId);
        ReplySuccessCode code = ReplySuccessCode.REPLY_READALL_SUCCESS;
        return ApiResponse.success(code.name(), code.getHttpStatus(), code.getMessage(), replyResponseDtos);
    }
}
