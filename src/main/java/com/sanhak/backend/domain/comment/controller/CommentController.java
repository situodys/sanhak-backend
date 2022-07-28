package com.sanhak.backend.domain.comment.controller;

import com.sanhak.backend.domain.comment.dto.CommentDTO;
import com.sanhak.backend.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/remove/{id}")
    public Long remove(@PathVariable("id")Long id){
        return commentService.remove(id);
    }

    @PostMapping("/add")
    public Long insert(@RequestBody CommentDTO dto){
        return commentService.insert(dto);
    }


}
