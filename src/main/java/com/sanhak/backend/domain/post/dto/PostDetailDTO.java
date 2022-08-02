package com.sanhak.backend.domain.post.dto;

import com.sanhak.backend.domain.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDetailDTO {
    private String cafeName;
    private String categoryName;
    private String title;
    private String author;
    private String content;
    private LocalDateTime registerAt;
    private String link;
    private Double similarity;
    private List<Comment> comments;

//    @Builder
//    public PostDTO(String cafeName, String categoryName, String title, String author, String content, LocalDateTime registerAt, String link, Double similarity) {
//        this.cafeName = cafeName;
//        this.categoryName = categoryName;
//        this.title = title;
//        this.author = author;
//        this.content = content;
//        this.registerAt = registerAt;
//        this.link = link;
//        this.similarity = similarity;
//    }

}


