package com.sanhak.backend.domain.post.repository;

import com.sanhak.backend.domain.comment.Comment;
import com.sanhak.backend.domain.comment.repository.CommentRepository;
import com.sanhak.backend.domain.post.Post;
import com.sanhak.backend.global.PageRequestDTO;
import com.sanhak.backend.global.QuerydslConfig;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;


import javax.persistence.EntityManager;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

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
        generatePosts(1,25);
        generateComments(1L,1,5);
    }

    private void generateComments(Long PostId, int start, int end) {
        IntStream.rangeClosed(start,end)
                .forEach(i->{
                    Comment comment = Comment.builder()
                            .content("content.." + i)
                            .post(Post.builder().id(PostId).build())
                            .build();
                    commentRepository.save(comment);
                });
    }

    private void generatePosts(int start, int end) {
        LongStream.rangeClosed(start,end)
                        .forEach(id->{
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

    @Test
    @DisplayName("기본 페이징 처리된 Post list 반환")
    public void getPostList_test() throws Exception{
        //give
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .pageable(PageRequest.of(0, 10, Sort.by("id")))
                .searchType(null)
                .searchKeyword(null)
                .totalCount(-1)
                .build();


        //when
        PageImpl<Object[]> result = postRepository.getPostList(pageRequestDTO);

        //then
        assertThat(result.getTotalElements()).isEqualTo(25);
        assertThat(result.getTotalPages()).isEqualTo(3);
        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    @DisplayName("제목에 1이 들어간 Post list 반환")
    public void getPostList_test2() throws Exception{
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .pageable(PageRequest.of(0, 10, Sort.by("id")))
                .searchType("t")
                .searchKeyword("1")
                .totalCount(-1)
                .build();

        //when
        PageImpl<Object[]> result = postRepository.getPostList(pageRequestDTO);

        //then
        assertThat(result.getTotalElements()).isEqualTo(12);
        assertThat(result.getTotalPages()).isEqualTo(2);
        for (Object[] objects : result) {
//            System.out.println(Arrays.toString(objects));
        }
    }
}