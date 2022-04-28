package com.daily.themoti.post.service;

import com.daily.themoti.global.exception.NoSuchPostExist;
import com.daily.themoti.post.domain.Post;
import com.daily.themoti.post.dto.PostRequestDto;
import com.daily.themoti.post.dto.PostResponseDto;
import com.daily.themoti.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final PostRepository postRepository;

    @Override
    public PostResponseDto create(PostRequestDto postRequestDto) {
        Post newPost = postRepository.save(postRequestDto.toEntity());
        return new PostResponseDto(newPost);
    }

    @Transactional
    @Override
    public PostResponseDto update(Long postId, PostRequestDto postRequestDto) {
        Post updatePost = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchPostExist());
        updatePost.update(postRequestDto.getTitle(), postRequestDto.getUserId(), postRequestDto.getContent());
        postRepository.save(updatePost);
        return new PostResponseDto(updatePost);
    }

    @Override
    public void delete(Long postId) {
        try {
            postRepository.deleteById(postId);
        } catch (IllegalArgumentException e) {
            throw new NoSuchPostExist();
        }
    }

    @Override
    public List<PostResponseDto> readAll() {
        List<Post> posts = postRepository.findAll();
        List<PostResponseDto> postResponseDtos = new ArrayList<>();

        for (Post post : posts) {
            postResponseDtos.add(new PostResponseDto(post));
        }

        return postResponseDtos;
    }

    @Override
    public PostResponseDto readOne(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchPostExist());
        return new PostResponseDto(post);
    }
}
