package com.sanhak.backend.domain.comment;

import com.sanhak.backend.domain.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "comment")
@ToString(exclude = "post")
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(String content, Post post) {
        this.content = content;
        this.post = post;
    }
}
