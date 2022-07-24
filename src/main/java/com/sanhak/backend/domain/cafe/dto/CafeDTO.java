package com.sanhak.backend.domain.cafe.dto;


import lombok.Getter;

@Getter
public class CafeDTO {
    private String cafeName;
    private String categoryName;

    public CafeDTO(String cafeName, String categoryName) {
        this.cafeName = cafeName;
        this.categoryName = categoryName;
    }
}
