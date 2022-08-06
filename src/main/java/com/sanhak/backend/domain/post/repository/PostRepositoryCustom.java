package com.sanhak.backend.domain.post.repository;

import com.sanhak.backend.domain.post.Post;
import com.sanhak.backend.global.PageRequestDTO;
import org.springframework.data.domain.PageImpl;

import java.util.Optional;

public interface PostRepositoryCustom {
    Optional<Post> findPostDetailByPostId(Long id);

    PageImpl<Object[]> getPostList(PageRequestDTO pageRequestDTO);
}
