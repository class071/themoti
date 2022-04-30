package com.daily.themoti.post.service;

import com.daily.themoti.global.exception.NoSuchPostExist;
import com.daily.themoti.post.domain.Post;
import com.daily.themoti.post.dto.PostCreateRequestDto;
import com.daily.themoti.post.dto.PostResponseDto;
import com.daily.themoti.post.dto.PostUpdateRequestDto;
import com.daily.themoti.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Transactional
    @Override
    public PostResponseDto create(PostCreateRequestDto postCreateRequestDto) {
        Post newPost = postRepository.save(postCreateRequestDto.toEntity());
        return new PostResponseDto(newPost);
    }

    @Override
    public PostResponseDto readOne(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchPostExist());
        return new PostResponseDto(post);
    }

    @Transactional
    @Override
    public void delete(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchPostExist());
        postRepository.delete(post);
    }

    @Transactional
    @Override
    public PostResponseDto update(Long postId, PostUpdateRequestDto postUpdateRequestDto) {
        Post updatePost = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchPostExist());
        updatePost.update(postUpdateRequestDto.getTitle(), postUpdateRequestDto.getContent());
        return new PostResponseDto(updatePost);
    }

    @Override
    public List<PostResponseDto> readAll() {
        List<Post> posts = postRepository.findAll();
        List<PostResponseDto> postResponseDtos = posts.stream().map(post -> new PostResponseDto(post)).collect(Collectors.toList());
        return postResponseDtos;
    }
}
