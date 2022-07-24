package com.sanhak.backend.domain.post.service;

import com.sanhak.backend.domain.post.Post;
import com.sanhak.backend.domain.post.dto.PostDTO;
import com.sanhak.backend.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public Page<PostDTO> findByPagination(Pageable pageable) {
        return postRepository
                .findAll(pageable)
                .map(post -> modelMapper.map(post, PostDTO.class));
    }

    public PostDTO findById(Long id) {
        Post post = postRepository
                .findByPostId(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 post가 없습니다."));
        return modelMapper.map(post, PostDTO.class);
    }

    public void removeById(Long id) {
        postRepository.removeById(id);
    }
}
