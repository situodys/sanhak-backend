package com.sanhak.backend.domain.cafe.service;


import com.sanhak.backend.domain.cafe.Cafe;
import com.sanhak.backend.domain.cafe.dto.CafeDTO;
import com.sanhak.backend.domain.cafe.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CafeService {

    private final CafeRepository cafeRepository;


    public void add(CafeDTO dto){
        Cafe newCafe = new Cafe(dto.getCafeName(), dto.getCategoryName());
        cafeRepository.save(newCafe);
    }

    public void sub(CafeDTO dto) {
        cafeRepository.removeByCafeNameAndCategoryName(dto.getCafeName(), dto.getCategoryName());
    }
}
