package com.daily.themoti.community.service;

import com.daily.themoti.community.dto.PostCreateRequestDto;
import com.daily.themoti.community.dto.PostResponseDto;
import com.daily.themoti.community.dto.PostUpdateRequestDto;

import java.util.List;

public interface PostService {

    PostResponseDto create(PostCreateRequestDto postCreateRequestDto);

    PostResponseDto readOne(Long postId);

    void delete(Long postId);

    PostResponseDto update(Long postId, PostUpdateRequestDto postUpdateRequestDto);

    List<PostResponseDto> readAll();
}
