package com.daily.themoti.post.controller;

import com.daily.themoti.global.api.ApiResponse;
import com.daily.themoti.global.constant.PostSuccessCode;
import com.daily.themoti.post.dto.PostCreateRequestDto;
import com.daily.themoti.post.dto.PostResponseDto;
import com.daily.themoti.post.dto.PostUpdateRequestDto;
import com.daily.themoti.post.dto.ReplyResponseDto;
import com.daily.themoti.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/api/post")
    public ApiResponse<PostResponseDto> create(@Valid @RequestBody PostCreateRequestDto postCreateRequestDto) {
        PostResponseDto postResponseDto = postService.create(postCreateRequestDto);
        PostSuccessCode code = PostSuccessCode.POST_CREATE_SUCCESS;
        return ApiResponse.success(code.name(), code.getHttpStatus(), code.getMessage(), postResponseDto);
    }

    @GetMapping("/api/post/{postId}")
    public ApiResponse<PostResponseDto> readOne(@PathVariable Long postId) {
        PostResponseDto postResponseDto = postService.readOne(postId);
        PostSuccessCode code = PostSuccessCode.POST_READONE_SUCCESS;
        return ApiResponse.success(code.name(), code.getHttpStatus(), code.getMessage(), postResponseDto);
    }

    @DeleteMapping("/api/post/{postId}")
    public ApiResponse<ReplyResponseDto> delete(@PathVariable Long postId) {
        postService.delete(postId);
        PostSuccessCode code = PostSuccessCode.POST_DELETE_SUCCESS;
        return ApiResponse.success(code.name(), code.getHttpStatus(), code.getMessage(), postId);
    }

    @PutMapping("/api/post/{postId}")
    public ApiResponse<PostResponseDto> update(@PathVariable Long postId, @Valid @RequestBody PostUpdateRequestDto postUpdateRequestDto) {
        PostResponseDto postResponseDto = postService.update(postId, postUpdateRequestDto);
        PostSuccessCode code = PostSuccessCode.POST_UPDATE_SUCCESS;
        return ApiResponse.success(code.name(), code.getHttpStatus(), code.getMessage(), postResponseDto);
    }

    @GetMapping("/api/post")
    public ApiResponse<List<PostResponseDto>> readAll() {
        List<PostResponseDto> postResponseDtos = postService.readAll();
        PostSuccessCode code = PostSuccessCode.POST_READALL_SUCCESS;
        return ApiResponse.success(code.name(), code.getHttpStatus(), code.getMessage(), postResponseDtos);
    }
}
