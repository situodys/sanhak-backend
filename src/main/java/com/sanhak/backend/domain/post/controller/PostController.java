package com.sanhak.backend.domain.post.controller;

import com.sanhak.backend.domain.post.dto.PostDTO;
import com.sanhak.backend.domain.post.dto.PostDetailDTO;
import com.sanhak.backend.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // pagination
    @GetMapping("/")
    public Page<PostDTO> findByPagination(Pageable pageable){
        return postService.findByPagination(pageable);
    }

    @GetMapping("/detail/{id}")
    public PostDetailDTO findById(@PathVariable("id") Long id){
        return postService.findPostDetailByPostId(id);
    }

    @GetMapping("/detail/remove/{id}")
    public String removeById(@PathVariable("id") Long id) {
        postService.removeById(id);
        return String.format("success : %d post removed", id);
    }
}
