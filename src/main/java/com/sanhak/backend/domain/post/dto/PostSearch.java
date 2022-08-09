package com.sanhak.backend.domain.post.dto;

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

}
