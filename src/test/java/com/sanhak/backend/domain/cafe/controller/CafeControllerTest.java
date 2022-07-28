package com.sanhak.backend.domain.cafe.controller;

import com.sanhak.backend.domain.cafe.dto.CafeDTO;
import com.sanhak.backend.domain.cafe.repository.CafeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class CafeControllerTest {

    @Autowired
    private CafeController cafeController;

    @Test
    @DisplayName("Cafe add, sub : Cafe 추가 삭제 테스트")
    void test1() throws Exception{
        //given
        CafeDTO dto = new CafeDTO();
        dto.setCafeName("hello");
        dto.setCategoryName("world");

        //when
        Long addCafeId = cafeController.add(dto);
        assertThat(addCafeId).isGreaterThan(0L);

        Long subCafeId = cafeController.sub(addCafeId);
        assertThat(subCafeId).isEqualTo(addCafeId);
    }

    @Test
    @DisplayName("Cafe Pagination : Cafe Pagination 테스트")
    void test2() throws Exception{
        //given
        PageRequest req = PageRequest.of(0, 10);
        CafeDTO dto = new CafeDTO();
        dto.setCafeName("hello");
        dto.setCategoryName("world");

        //when
        Page<CafeDTO> cafePage = cafeController.listByPagination(req);

        //then
        assertThat(cafePage.getTotalElements()).isEqualTo(0L);
        assertThat(cafePage.getTotalPages()).isEqualTo(0L);

        Long savedId = cafeController.add(dto);// cafe add

        cafePage = cafeController.listByPagination(req);

        assertThat(cafePage.getTotalElements()).isEqualTo(1L);
        assertThat(cafePage.getTotalPages()).isEqualTo(1L);
    }
}