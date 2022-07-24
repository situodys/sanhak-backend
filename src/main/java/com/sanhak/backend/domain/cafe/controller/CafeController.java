package com.sanhak.backend.domain.cafe.controller;

import com.sanhak.backend.domain.cafe.dto.CafeDTO;
import com.sanhak.backend.domain.cafe.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/cafe")
@RestController
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;

    @PostMapping("/add")
    public String add(@RequestBody CafeDTO dto){
        cafeService.add(dto);
        return "success : add new cafe";
    }

    @PostMapping("/sub")
    public String sub(@RequestBody CafeDTO dto){
        cafeService.sub(dto);
        return "success : sub origin cafe";
    }
}
