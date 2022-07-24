package com.sanhak.backend.domain.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
public class PostDTO {
    private String cafeName;
    private String categoryName;
    private String title;
    private String author;
    private String content;
    private LocalDateTime registerAt;
    private String link;
    private Double similarity;

    @Builder
    public PostDTO(String cafeName, String categoryName, String title, String author, String content, LocalDateTime registerAt, String link, Double similarity) {
        this.cafeName = cafeName;
        this.categoryName = categoryName;
        this.title = title;
        this.author = author;
        this.content = content;
        this.registerAt = registerAt;
        this.link = link;
        this.similarity = similarity;
    }

}
