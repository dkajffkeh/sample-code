package com.patrick.jpasample.primary.service.manufacturer;

import com.patrick.jpasample.dto.response.ManufacturerIntegratedResponseDto;
import com.patrick.jpasample.primary.repository.manufacturer.ManufacturerCustomRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ManufacturerService {

    private final ManufacturerCustomRepository manufacturerCustomRepository;

    public List<ManufacturerIntegratedResponseDto> getAllProfiles() {
        return this.manufacturerCustomRepository.getAllProfiles();
    }
}
