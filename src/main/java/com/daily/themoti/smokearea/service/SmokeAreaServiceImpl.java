package com.daily.themoti.smokearea.service;

import com.daily.themoti.smokearea.SmokeArea;
import com.daily.themoti.smokearea.dto.SaveAreaRequestDto;
import com.daily.themoti.smokearea.dto.SmokeAreaResponseDto;
import com.daily.themoti.smokearea.repository.SmokeAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmokeAreaServiceImpl implements SmokeAreaService{

    private final SmokeAreaRepository smokeAreaRepository;

    public SmokeAreaResponseDto save(SaveAreaRequestDto saveAreaRequestDto){
        SmokeArea smokeArea = smokeAreaRepository.save(saveAreaRequestDto.toEntity());
        return new SmokeAreaResponseDto(smokeArea);
    }
}
