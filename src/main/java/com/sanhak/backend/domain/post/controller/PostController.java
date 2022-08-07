package com.sanhak.backend.domain.post.controller;

import com.sanhak.backend.domain.post.dto.PostDTO;
import com.sanhak.backend.domain.post.dto.PostDetailDTO;
import com.sanhak.backend.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public Page<PostDTO> findByPagination(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "title", defaultValue = "") String title
    ) {
        PageRequest pr = PageRequest.of(page, size);
        return postService.findByPagination(pr, title);
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
