package com.sanhak.backend.domain.post.repository;

import com.sanhak.backend.domain.post.Post;
import com.sanhak.backend.domain.post.dto.PostSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostRepositoryCustom {
    Optional<Post> findPostDetailByPostId(Long id);
    Page<Post> findByPaginationWithQuerydsl(PostSearch postSearch);
}
