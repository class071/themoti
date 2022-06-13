package com.daily.themoti.community.post.service;

import com.daily.themoti.community.exception.NoSuchPostExist;
import com.daily.themoti.community.post.domain.Post;
import com.daily.themoti.community.post.dto.PostCreateRequestDto;
import com.daily.themoti.community.post.dto.PostResponseDto;
import com.daily.themoti.community.post.dto.PostUpdateRequestDto;
import com.daily.themoti.community.post.repository.PostRepository;
import com.daily.themoti.user.domain.User;
import com.daily.themoti.user.exception.UserNotExistException;
import com.daily.themoti.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public PostResponseDto create(PostCreateRequestDto postCreateRequestDto) {
        Post newPost = postRepository.save(postCreateRequestDto.toEntity());
        User user = userRepository.findById(postCreateRequestDto.getUserId())
                .orElseThrow(() -> new UserNotExistException());
        newPost.setUserProfile(user.getUsername(), user.getThumbnailURL());
        return new PostResponseDto(newPost);
    }

    @Override
    public PostResponseDto readOne(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchPostExist());
        return new PostResponseDto(post);
    }

    @Transactional
    @PreAuthorize("postGuard.check(#postId)")
    @Override
    public void delete(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchPostExist());
        postRepository.delete(post);
    }

    @Transactional
    @PreAuthorize("@postGuard.check(#postId)")
    @Override
    public PostResponseDto update(Long postId, PostUpdateRequestDto postUpdateRequestDto) {
        Post updatePost = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchPostExist());
        updatePost.update(postUpdateRequestDto.getContent());
        return new PostResponseDto(updatePost);
    }

    @Override
    public List<PostResponseDto> readAll() {
        List<Post> posts = postRepository.findAll();
        List<PostResponseDto> postResponseDtos = posts.stream()
                .map(post -> new PostResponseDto(post))
                .collect(Collectors.toList());
        return postResponseDtos;
    }
}
