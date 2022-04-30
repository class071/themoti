package com.daily.themoti.post.service;

import com.daily.themoti.post.dto.PostCreateRequestDto;
import com.daily.themoti.post.dto.PostResponseDto;
import com.daily.themoti.post.dto.PostUpdateRequestDto;

import java.util.List;

public interface PostService {

    PostResponseDto create(PostCreateRequestDto postCreateRequestDto);

    PostResponseDto readOne(Long postId);

    void delete(Long postId);

    PostResponseDto update(Long postId, PostUpdateRequestDto postUpdateRequestDto);

    List<PostResponseDto> readAll();
}
