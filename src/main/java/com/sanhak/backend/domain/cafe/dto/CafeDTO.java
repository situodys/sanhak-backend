package com.sanhak.backend.domain.cafe.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CafeDTO {
    private Long id;
    private String cafeName;
    private String categoryName;
}
