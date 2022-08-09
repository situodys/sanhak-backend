package com.sanhak.backend.domain.post.controller;

import com.sanhak.backend.domain.post.dto.PostDTO;
import com.sanhak.backend.domain.post.dto.PostDetailDTO;
import com.sanhak.backend.domain.post.dto.PostSearch;
import com.sanhak.backend.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public Page<PostDTO> findByPagination(
            @Validated @RequestBody
            PostSearch postSearch
    ) {
        return postService.findByPagination(postSearch);
    }

    @GetMapping("/detail/{id}")
    public PostDetailDTO findById(@PathVariable("id") Long id) {
        return postService.findPostDetailByPostId(id);
    }

    @GetMapping("/detail/remove/{id}")
    public String removeById(@PathVariable("id") Long id) {
        postService.removeById(id);
        return String.format("success : %d post removed", id);
    }
}
