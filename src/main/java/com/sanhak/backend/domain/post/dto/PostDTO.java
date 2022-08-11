package com.sanhak.backend.domain.post.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PostDTO {
    private String cafeName;
    private String categoryName;
    private String title;
    private String author;
    private String content;
    private LocalDateTime registerAt;
    private String link;
    private Double similarity;
}
