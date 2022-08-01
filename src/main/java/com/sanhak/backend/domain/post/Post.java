package com.sanhak.backend.domain.post;


import com.sanhak.backend.domain.comment.Comment;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post")
@ToString(exclude = "comment")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "cafe_name")
    private String cafeName;

    @Column(name = "category_name")
    private String categoryName;

    private String title;
    private String author;
    private String content;

    @Column(name = "register_at")
    private LocalDateTime registerAt;

    private String link;

    private Double similarity;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> comment = new ArrayList<>();

    @Builder
    public Post(Long id, String cafeName, String categoryName, String title, String author, String content, LocalDateTime registerAt, String link, Double similarity, List<Comment> comment) {
        this.id = id;
        this.cafeName = cafeName;
        this.categoryName = categoryName;
        this.title = title;
        this.author = author;
        this.content = content;
        this.registerAt = registerAt;
        this.link = link;
        this.similarity = similarity;
        this.comment = comment;
    }
}
