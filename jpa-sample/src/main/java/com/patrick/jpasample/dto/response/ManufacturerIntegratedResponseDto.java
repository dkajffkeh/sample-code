package com.patrick.jpasample.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ManufacturerIntegratedResponseDto {

    private final Long manufacturerProfileId;
    private final List<ManufacturerLanguageProfileResponseDto> languageProfiles;

    @Getter
    @AllArgsConstructor
    public static class ManufacturerLanguageProfileResponseDto {
        private final Long manufacturerLanguageProfileId;
        private final String languageProfile;
    }
}
