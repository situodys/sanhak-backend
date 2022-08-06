package com.sanhak.backend.global;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    private Pageable pageable;
    
    private String searchType;
    private String searchKeyword;

    private int totalCount;

    public Sort getSort() {
        return pageable.getSort();
    }

    public int getSize(){
        return pageable.getPageSize();
    }

    public long getOffset() {
        return pageable.getOffset();
    }
}
