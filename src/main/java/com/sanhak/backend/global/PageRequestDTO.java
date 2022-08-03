package com.sanhak.backend.global;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    private int page;
    private int size;
    
    private String searchType;
    private String searchKeyword;

    //검색에서 마지막 결과의 id
    private Long lastId;
    private int totalSize;
}
