package com.sanhak.backend.domain.cafe.service;


import com.sanhak.backend.domain.cafe.Cafe;
import com.sanhak.backend.domain.cafe.dto.CafeDTO;
import com.sanhak.backend.domain.cafe.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CafeService {

    private final CafeRepository cafeRepository;

    public Long add(CafeDTO dto){
        Cafe newCafe = new Cafe(dto.getCafeName(), dto.getCategoryName());
        return cafeRepository.save(newCafe).getId();
    }

    public Long sub(Long id) {
        cafeRepository.removeById(id);
        return id;
    }

    public Page<Cafe> listByPagination(Pageable pageable) {
        return cafeRepository.findAll(pageable);
    }
}
