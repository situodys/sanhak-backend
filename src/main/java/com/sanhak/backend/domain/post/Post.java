package com.sanhak.backend.domain.post;


import com.sanhak.backend.domain.commnet.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "post")
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

    @OneToMany(mappedBy = "post")
    private List<Comment> comment = new ArrayList<>();
}
