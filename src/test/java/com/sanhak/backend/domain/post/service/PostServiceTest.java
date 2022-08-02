package com.sanhak.backend.domain.post.service;

import com.sanhak.backend.domain.post.dto.PostDetailDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    public void findPostDetailByPostId() throws Exception{
        //give
        Long postId= 1L;

        //when
        PostDetailDTO result = postService.findPostDetailByPostId(postId);

        //then
    }

}