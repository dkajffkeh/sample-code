package com.patrick.jpasample.primary.repository.manufacturer;

import static com.patrick.jpasample.primary.entity.manufacturer.QManufacturerLanguageProfile.manufacturerLanguageProfile;
import static com.patrick.jpasample.primary.entity.manufacturer.QManufacturerProfile.manufacturerProfile;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.types.Projections.list;

import com.patrick.jpasample.dto.response.ManufacturerIntegratedResponseDto;
import com.patrick.jpasample.dto.response.ManufacturerIntegratedResponseDto.ManufacturerLanguageProfileResponseDto;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ManufacturerCustomRepository {

    private final JPAQueryFactory primaryFactory;

    public List<ManufacturerIntegratedResponseDto> getAllProfiles() {
        return primaryFactory.from(manufacturerProfile)
                .join(manufacturerLanguageProfile)
                .on(manufacturerProfile.manufacturerProfileId.eq(manufacturerLanguageProfile.manufacturerProfileId))
                .transform(groupBy(manufacturerProfile.manufacturerProfileId).list(
                        Projections.constructor(
                                ManufacturerIntegratedResponseDto.class,
                                manufacturerProfile.manufacturerProfileId,
                                GroupBy.list(Projections.constructor(
                                        ManufacturerLanguageProfileResponseDto.class,
                                        manufacturerLanguageProfile.manufacturerLanguageProfileId,
                                        manufacturerLanguageProfile.languageCode
                                ))
                        )
                ));
    }

}
