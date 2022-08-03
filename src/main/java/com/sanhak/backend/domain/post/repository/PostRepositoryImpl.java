package com.sanhak.backend.domain.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sanhak.backend.domain.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import static com.sanhak.backend.domain.comment.QComment.comment;
import static com.sanhak.backend.domain.post.QPost.post;


@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Post> findPostDetailByPostId(Long id){

        Post result= jpaQueryFactory.select(post)
                .from(post)
                .leftJoin(post.comment,comment)
                .fetchJoin()
                .where(post.id.eq(id))
                .fetchOne();

        return Optional.ofNullable(result);
    }
}
