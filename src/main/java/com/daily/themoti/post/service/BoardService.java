package com.daily.themoti.post.service;

import com.daily.themoti.post.dto.PostRequestDto;
import com.daily.themoti.post.dto.PostResponseDto;

import java.util.List;

public interface BoardService {

    PostResponseDto create(PostRequestDto postRequestDto);

    PostResponseDto update(Long postId, PostRequestDto postRequestDto);

    void delete(Long postId);

    List<PostResponseDto> readAll();

    PostResponseDto readOne(Long postId);
}
