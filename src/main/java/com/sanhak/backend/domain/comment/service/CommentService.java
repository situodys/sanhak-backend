package com.sanhak.backend.domain.comment.service;

import com.sanhak.backend.domain.comment.Comment;
import com.sanhak.backend.domain.comment.dto.CommentDTO;
import com.sanhak.backend.domain.comment.repository.CommentRepository;
import com.sanhak.backend.domain.post.Post;
import com.sanhak.backend.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public Long remove(Long id) {
        commentRepository.removeById(id);
        return id;
    }

    public Long insert(CommentDTO dto) {
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("해당하는 Post가 없습니다."));
        Comment newComment = new Comment(dto.getContent(), post);
        return commentRepository.save(newComment).getId();
    }
}
