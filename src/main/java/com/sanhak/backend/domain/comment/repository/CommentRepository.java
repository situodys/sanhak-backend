package com.sanhak.backend.domain.comment.repository;

import com.sanhak.backend.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    void removeById(Long id);
}
