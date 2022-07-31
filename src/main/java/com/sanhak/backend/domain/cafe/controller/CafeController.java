package com.sanhak.backend.domain.cafe.controller;

import com.sanhak.backend.domain.cafe.dto.CafeDTO;
import com.sanhak.backend.domain.cafe.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/cafe")
@RestController
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;
    private final ModelMapper modelMapper;

    @GetMapping("/")
    public Page<CafeDTO> listByPagination(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return cafeService
                .listByPagination(PageRequest.of(page, size))
                .map(cafe -> modelMapper.map(cafe, CafeDTO.class));
    }

    @PostMapping("/add")
    public Long add(@RequestBody CafeDTO dto) {
        return cafeService.add(dto);
    }

    @PostMapping("/sub/{id}")
    public Long sub(@PathVariable("id") Long id) {
        return cafeService.sub(id);
    }
}
