package com.daily.themoti.post.controller;

import com.daily.themoti.global.api.ApiResponse;
import com.daily.themoti.post.dto.PostRequestDto;
import com.daily.themoti.post.dto.PostResponseDto;
import com.daily.themoti.post.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/board")
@RestController
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/")
    public ApiResponse<PostResponseDto> create(@RequestBody PostRequestDto postRequestDto) {
        PostResponseDto postResponseDto = boardService.create(postRequestDto);
        return ApiResponse.success(null, null, null, postResponseDto);
    }

    @PutMapping("/{postId}")
    public ApiResponse<PostResponseDto> update(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto) {
        PostResponseDto postResponseDto = boardService.update(postId, postRequestDto);
        return ApiResponse.success(null, null, null, postResponseDto);
    }

    @DeleteMapping("/{postId}")
    public ApiResponse<?> delete(@PathVariable Long postId) {
        boardService.delete(postId);
        return ApiResponse.success(null, null, null, null);
    }

    @GetMapping("/readOne/{postId}")
    public ApiResponse<PostResponseDto> readOne(@PathVariable Long postId) {
        PostResponseDto postResponseDto = boardService.readOne(postId);
        return ApiResponse.success(null, null, null, postResponseDto);
    }

    @GetMapping("/readAll")
    public ApiResponse<PostResponseDto> readAll() {
        List<PostResponseDto> postResponseDtos = boardService.readAll();
        return ApiResponse.success(null, null, null, postResponseDtos);
    }
}
