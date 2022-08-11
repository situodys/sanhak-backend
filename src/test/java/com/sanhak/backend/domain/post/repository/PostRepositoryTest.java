package com.sanhak.backend.domain.post.repository;

import com.sanhak.backend.domain.comment.Comment;
import com.sanhak.backend.domain.comment.repository.CommentRepository;
import com.sanhak.backend.domain.post.Post;

import com.sanhak.backend.domain.post.dto.PostSearch;
import com.sanhak.backend.global.QuerydslConfig;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import org.springframework.data.domain.Page;

import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

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
    public void init() {
        LongStream.rangeClosed(1, 25)
                .forEach(id -> {
                    Post post = Post.builder()
                            .cafeName("cafe" + id)
                            .categoryName("category" + id)
                            .title("title" + id)
                            .author("author" + id)
                            .content("content" + id)
                            .link("link" + id)
                            .similarity(0.1)
                            .registerAt(LocalDateTime.of(2022, 1, 1, 11, 11))
                            .build();
                    postRepository.save(post);
                });

        Long postId = 1L;

        IntStream.rangeClosed(1, 5)
                .forEach(i -> {
                    Comment comment = Comment.builder()
                            .content("reply.." + i)
                            .post(Post.builder().id(postId).build())
                            .build();
                    commentRepository.save(comment);
                });
    }


    @Test
    @DisplayName("postId로 검색_해당 포스트가 있는 경우")
    void findPostDetailByPostId_exist() {
        Long postId = 1L;

        Optional<Post> result = postRepository.findPostDetailByPostId(postId);

        assertThat(result.isPresent()).isEqualTo(true);
        Post post = result.get();
        List<Comment> comments = post.getComment();
        for (int i = 0; i < comments.size(); i++) {
            assertThat(comments.get(i).getId()).isEqualTo(i + 1);
            assertThat(comments.get(i).getContent()).isEqualTo("reply.." + (i + 1));
        }
    }

    @Test
    @DisplayName("postId로 검색_해당 포스트가 없는 경우")
    public void findPostDetailByPostId_notExist() throws Exception {
        //give
        Long postId = 0L;
        //when
        Optional<Post> result = postRepository.findPostDetailByPostId(postId);
        assertThat(result.isPresent()).isEqualTo(false);
        //then
    }

    @Test
    @DisplayName("pagination : 여러 파라미터를 이용해서 검색을 해도 괜찮은지 검증")
            void findByPaginationAndSearch() throws Exception {
        //given
        String cafeName = "cafe1";
        String categoryName = "category1";
        String title = "title1";
        String author = "author1";

        PostSearch postSearchJustCafeName = PostSearch.builder()
                .cafeName(cafeName).build();
        PostSearch postSearchJustCategoryName = PostSearch.builder()
                .categoryName(categoryName).build();
        PostSearch postSearchJustTitle = PostSearch.builder()
                .title(title).build();
        PostSearch postSearchJustAuthor = PostSearch.builder()
                .author(author).build();
        PostSearch defaultPostSearch = PostSearch.builder().build();

        //when
        Page<Post> resultByDefault = postRepository.findByPaginationWithQuerydsl(defaultPostSearch);
        Page<Post> resultByCafeName = postRepository.findByPaginationWithQuerydsl(postSearchJustCafeName);
        Page<Post> resultByCategoryName = postRepository.findByPaginationWithQuerydsl(postSearchJustCategoryName);
        Page<Post> resultByTitle = postRepository.findByPaginationWithQuerydsl(postSearchJustTitle);
        Page<Post> resultByAuthor = postRepository.findByPaginationWithQuerydsl(postSearchJustAuthor);

        //then
        for (Post post : resultByDefault) {
            System.out.println(post);
        }
        assertThat(resultByDefault.getTotalElements()).isEqualTo(25L);
        assertThat(resultByDefault.getTotalPages()).isEqualTo(3);
        assertThat(resultByCafeName.getTotalElements()).isEqualTo(11L);
        assertThat(resultByCategoryName.getTotalElements()).isEqualTo(11L);
        assertThat(resultByTitle.getTotalElements()).isEqualTo(11L);
        assertThat(resultByAuthor.getTotalElements()).isEqualTo(11L);

    }
}
