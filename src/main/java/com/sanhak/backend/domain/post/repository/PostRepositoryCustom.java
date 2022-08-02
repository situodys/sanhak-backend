package com.sanhak.backend.domain.post.repository;

import com.sanhak.backend.domain.post.Post;

import java.util.Optional;

public interface PostRepositoryCustom {
    Optional<Post> findPostDetailByPostId(Long id);
}
