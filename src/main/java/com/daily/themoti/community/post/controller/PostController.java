package com.daily.themoti.community.post.controller;

import com.daily.themoti.global.api.ApiResponse;
import com.daily.themoti.community.post.dto.PostCreateRequestDto;
import com.daily.themoti.community.post.dto.PostResponseDto;
import com.daily.themoti.community.post.dto.PostUpdateRequestDto;
import com.daily.themoti.community.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        return ApiResponse.success(HttpStatus.CREATED, postResponseDto);
    }

    @GetMapping("/api/post/{postId}")
    public ApiResponse<PostResponseDto> readOne(@PathVariable Long postId) {
        PostResponseDto postResponseDto = postService.readOne(postId);
        return ApiResponse.success(HttpStatus.OK, postResponseDto);
    }

    @DeleteMapping("/api/post/{postId}")
    public ApiResponse<PostResponseDto> delete(@PathVariable Long postId) {
        postService.delete(postId);
        return ApiResponse.success(HttpStatus.OK, postId);
    }

    @PutMapping("/api/post/{postId}")
    public ApiResponse<PostResponseDto> update(@PathVariable Long postId, @Valid @RequestBody PostUpdateRequestDto postUpdateRequestDto) {
        PostResponseDto postResponseDto = postService.update(postId, postUpdateRequestDto);
        return ApiResponse.success(HttpStatus.CREATED, postResponseDto);
    }

    @GetMapping("/api/post")
    public ApiResponse<List<PostResponseDto>> readAll() {
        List<PostResponseDto> postResponseDtos = postService.readAll();
        return ApiResponse.success(HttpStatus.OK, postResponseDtos);
    }
}
