package com.sanhak.backend.domain.post.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.Optional;

@Data
@NoArgsConstructor
public class PostSearch {
    // 필수
    @Min(value = 0L)
    private Integer page;
    @Min(value = 1L)
    private Integer size;
    // 선택
    private String cafeName;
    private String categoryName;
    private String title;
    private String author;
    private LocalDateTime registerAt;

    public Integer getPage() {
        return page == null ? 0 : page;
    }

    public Integer getSize() {
        return size == null ? 10 : size;
    }

    public String getTitleRegex() {
        return title == null ? null : convertRegex(title);
    }

    public String getCafeNameRegex() {
        return cafeName == null ? null : convertRegex(cafeName);
    }

    public String getCategoryNameRegex() {
        return categoryName == null ? null : convertRegex(categoryName);
    }

    public String getAuthorRegex() {
        return author == null ? null : convertRegex(author);
    }

    private String convertRegex(String content) {
        return content + "%";
    }

    @Builder
    public PostSearch(Integer page, Integer size, String cafeName, String categoryName, String title, String author, LocalDateTime registerAt) {
        this.page = page;
        this.size = size;
        this.cafeName = cafeName;
        this.categoryName = categoryName;
        this.title = title;
        this.author = author;
        this.registerAt = registerAt;
    }
}
