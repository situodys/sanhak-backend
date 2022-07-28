package com.sanhak.backend.global.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerController {
    @GetMapping("/api/document")
    public String redirectSwagger(){
        return "redirect:/swagger-ui/index.html";
    }
}
