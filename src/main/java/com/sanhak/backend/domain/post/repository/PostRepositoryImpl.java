package com.sanhak.backend.domain.post.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sanhak.backend.domain.post.Post;
import com.sanhak.backend.global.PageRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public PageImpl<Object[]> getPostList(PageRequestDTO pageRequestDTO) {

        List<Long> ids;
        int totalCount= pageRequestDTO.getTotalCount();

        if (totalCount == -1) {
            totalCount = getTotalListCount(pageRequestDTO);
        }

        List<OrderSpecifier> orders = getOrderSpecifier(pageRequestDTO.getSort());
        ids = getPostListIds(pageRequestDTO,orders);
        if (ids.isEmpty()) {
            return null;
        }


        List<Tuple>  result = jpaQueryFactory.select(post.id,post.title,post.content, comment.count())
                .from(post)
                .leftJoin(comment).on(comment.post.eq(post))
                .where(
                        post.id.in(ids)
                )
                .orderBy(orders.stream().toArray(OrderSpecifier[]::new))
                .groupBy(post)
                .fetch();

        return new PageImpl<Object[]>(
                result.stream()
                        .map(tuple -> tuple.toArray())
                        .collect(Collectors.toList()), pageRequestDTO.getPageable(), totalCount
        );


    }

    private List<OrderSpecifier> getOrderSpecifier(Sort sort) {
        List<OrderSpecifier> orders = new ArrayList<>();

        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();
            PathBuilder orderExpression = new PathBuilder(Post.class, "post");
            orders.add(new OrderSpecifier(direction, orderExpression.get(prop)));
        });
        return orders;
    }

    private int getTotalListCount(PageRequestDTO pageRequestDTO) {
        String searchType = pageRequestDTO.getSearchType();
        String searchKeyword = pageRequestDTO.getSearchKeyword();


        return jpaQueryFactory.select(post.id)
                .from(post)
                .where(
                        post.id.gt(0L)
                        ,isTitleContainsKeyword(searchType, searchKeyword)
                        ,isContentContainsKeyword(searchType, searchKeyword)
                )
                .fetch().size();
    }

    private List<Long> getPostListIds(PageRequestDTO pageRequestDTO, List<OrderSpecifier> orders) {
        String searchType = pageRequestDTO.getSearchType();
        String searchKeyword = pageRequestDTO.getSearchKeyword();


        return jpaQueryFactory.select(post.id)
                .from(post)
                .where(
                        post.id.gt(0L)
                        , isTitleContainsKeyword(searchType, searchKeyword)
                        , isContentContainsKeyword(searchType, searchKeyword)
                )
                .offset(pageRequestDTO.getOffset())
                .limit(pageRequestDTO.getSize())
                .orderBy(orders.stream().toArray(OrderSpecifier[]::new))
                .fetch();

    }

    private BooleanExpression isTitleContainsKeyword(String searchType,String searchKeyword) {
        if (searchType==null || !searchType.contains("t")) {
            return null;
        }
        return post.title.containsIgnoreCase(searchKeyword);
    }

    private BooleanExpression isContentContainsKeyword(String searchType,String searchKeyword) {
        if (searchType==null || !searchType.contains("c")) {
            return null;
        }
        return post.content.containsIgnoreCase(searchKeyword);
    }
}
