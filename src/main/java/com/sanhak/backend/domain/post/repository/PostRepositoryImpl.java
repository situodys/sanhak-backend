package com.sanhak.backend.domain.post.repository;

import com.sanhak.backend.domain.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{

    private final EntityManager em;

    public Optional<Post> findByPostId(Long id){
        //querydsl 로 다시 구현해야한다.
        Post post = em.createQuery("select p from Post p join fetch p.comment where p.id=:id", Post.class)
                .setParameter("id", id)
                .getSingleResult();
        return Optional.ofNullable(post);
    }
}
