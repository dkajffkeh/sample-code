package com.patrick.jpasample.primary.api;

import com.patrick.jpasample.dto.FormulaScreeningAiAnalyzeCompleteRequestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class CosApiTestController {

    @PostMapping("/cos-api")
    public void test(@RequestBody FormulaScreeningAiAnalyzeCompleteRequestDto requestDto) {
        System.out.println(requestDto);
    }
}
