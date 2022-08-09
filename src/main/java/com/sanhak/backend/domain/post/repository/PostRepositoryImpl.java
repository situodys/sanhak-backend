package com.sanhak.backend.domain.post.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sanhak.backend.domain.post.Post;
import com.sanhak.backend.domain.post.dto.PostSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
    public Page<Post> findByPaginationWithQuerydsl(PostSearch postSearch) {

        PageRequest pageRequest = PageRequest.of(postSearch.getPage(), postSearch.getSize());

        String titleRegex = postSearch.getTitleRegex();
        String cafeNameRegex = postSearch.getCafeNameRegex();
        String categoryNameRegex = postSearch.getCategoryNameRegex();
        String authorRegex = postSearch.getAuthorRegex();


        JPAQuery<Post> query = jpaQueryFactory
                .select(post)
                .from(post)
                .where(likeTitle(titleRegex))
                .where(likeCafeName(cafeNameRegex))
                .where(likeCategoryName(categoryNameRegex))
                .where(likeAuthor(authorRegex));

        Long fetchCount = jpaQueryFactory
                .select(post.count())
                .from(post)
                .where(likeTitle(titleRegex))
                .where(likeCafeName(cafeNameRegex))
                .where(likeCategoryName(categoryNameRegex))
                .where(likeAuthor(authorRegex))
                .fetchOne();

        List<Post> postList = this.getQuerydsl().applyPagination(pageRequest, query).fetch();
        return new PageImpl<>(postList, pageRequest, fetchCount);
    }

    private BooleanExpression likeTitle(String titleRegex) {
        if (StringUtils.isNullOrEmpty(titleRegex)) return null;
        return post.title.like(titleRegex);
    }

    private BooleanExpression likeCafeName(String cafeNameRegex) {
        if (StringUtils.isNullOrEmpty(cafeNameRegex)) return null;
        return post.cafeName.like(cafeNameRegex);
    }

    private BooleanExpression likeCategoryName(String categoryNameRegex) {
        if (StringUtils.isNullOrEmpty(categoryNameRegex)) return null;
        return post.categoryName.like(categoryNameRegex);
    }

    private BooleanExpression likeAuthor(String authorRegex) {
        if (StringUtils.isNullOrEmpty(authorRegex)) return null;
        return post.author.like(authorRegex);
    }
}
