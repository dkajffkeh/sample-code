package com.patrick.jpasample.primary.api.manufacturer;

import com.patrick.jpasample.dto.response.ManufacturerIntegratedResponseDto;
import com.patrick.jpasample.primary.service.manufacturer.ManufacturerService;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @GetMapping("/manufacturers")
    public List<ManufacturerIntegratedResponseDto> getAllProfiles() {
        return manufacturerService.getAllProfiles();
    }

}
