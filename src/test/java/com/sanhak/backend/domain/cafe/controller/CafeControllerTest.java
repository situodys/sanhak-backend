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
    @DisplayName("Cafe Pagination : 카페를 하나 추가하고 다시 삭제를 했을 때 정상적으로 추가 삭제가 되는가")
    void test2() throws Exception{
        //given
        int page = 0;
        int size = 20;

        CafeDTO dto = new CafeDTO();
        dto.setCafeName("hello");
        dto.setCategoryName("world");

        //페이지 확인
        Page<CafeDTO> cafeDTOPage = cafeController.listByPagination(page, size);
        long previousTotalElement =  cafeDTOPage.getTotalElements();

        //추가
        Long savedId = cafeController.add(dto);
        cafeDTOPage = cafeController.listByPagination(page, size);

        assertThat(cafeDTOPage.getTotalElements()).isEqualTo(previousTotalElement + 1L);

        //삭제
        Long removedId = cafeController.sub(savedId);
        cafeDTOPage = cafeController.listByPagination(page, size);
        assertThat(cafeDTOPage.getTotalElements()).isEqualTo(previousTotalElement);

        assertThat(savedId).isEqualTo(removedId);// 추가된 카페와 삭제된 카페 아이디가 같아야한다.
    }
}