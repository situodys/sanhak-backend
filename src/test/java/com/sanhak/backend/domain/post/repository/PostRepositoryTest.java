package com.sanhak.backend.domain.post.repository;

import com.sanhak.backend.domain.comment.Comment;
import com.sanhak.backend.domain.comment.repository.CommentRepository;
import com.sanhak.backend.domain.post.Post;
import com.sanhak.backend.global.QuerydslConfig;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;


import javax.persistence.EntityManager;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



@DataJpaTest
@ActiveProfiles("test")
@Import(QuerydslConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @BeforeAll
    public void init(){
        Post post = Post.builder()
                .id(1L)
                .cafeName("cafe1")
                .categoryName("category1")
                .title("title1")
                .author("author1")
                .content("content1")
                .link("link1")
                .similarity(0.1)
                .registerAt(LocalDateTime.of(2022, 1, 1, 11, 11))
                .build();

        postRepository.save(post);

        IntStream.rangeClosed(1,5)
                .forEach(i->{
                    Comment comment = Comment.builder()
                            .content("content.." + i)
                            .post(Post.builder().id(1L).build())
                            .build();
                    commentRepository.save(comment);
                });
    }

    @Test
    @DisplayName("postId로 검색_해당 포스트가 있는 경우")
    void findPostDetailByPostId_exist() {
        Long postId= 1L;

        Optional<Post> result = postRepository.findPostDetailByPostId(postId);

        assertThat(result.isPresent()).isEqualTo(true);
        Post post = result.get();
        List<Comment> comments = post.getComment();
        for(int i=0;i<comments.size();i++){
            assertThat(comments.get(i).getId()).isEqualTo(i+1);
            assertThat(comments.get(i).getContent()).isEqualTo("content.."+(i+1));
        }



    }

    @Test
    @DisplayName("postId로 검색_해당 포스트가 없는 경우")
    public void findPostDetailByPostId_notExist() throws Exception{
        //give
        Long postId=0L;
        //when
        Optional<Post> result = postRepository.findPostDetailByPostId(postId);
        assertThat(result.isPresent()).isEqualTo(false);
        //then
    }
}