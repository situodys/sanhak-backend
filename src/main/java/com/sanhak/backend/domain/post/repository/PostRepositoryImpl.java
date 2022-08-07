package com.sanhak.backend.domain.post.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sanhak.backend.domain.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.sanhak.backend.domain.comment.QComment.comment;
import static com.sanhak.backend.domain.post.QPost.post;


@Repository
public class PostRepositoryImpl extends QuerydslRepositorySupport implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public PostRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Post.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Optional<Post> findPostDetailByPostId(Long id) {

        Post result = jpaQueryFactory
                .select(post)
                .from(post)
                .leftJoin(post.comment, comment)
                .fetchJoin()
                .where(post.id.eq(id))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public Page<Post> findByPaginationWithQuerydsl(Pageable pageable, String regex) {
        JPAQuery<Post> query = jpaQueryFactory
                .select(post)
                .from(post)
                .where(post.title.like(regex));

        Long fetchCount = jpaQueryFactory
                .select(post.count())
                .from(post)
                .where(post.title.like(regex)).fetchOne();

        List<Post> postList = this.getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<>(postList, pageable, fetchCount);
    }
}
