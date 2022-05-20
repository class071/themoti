package com.daily.themoti.smokearea.service;

import com.daily.themoti.smokearea.domain.SmokeArea;
import com.daily.themoti.smokearea.dto.SmokeAreaResponseDto;
import com.daily.themoti.smokearea.repository.SmokeAreaRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SmokeAreaServiceImpl implements SmokeAreaService{

    private final SmokeAreaRepository smokeAreaRepository;

    @Override
    public List<SmokeAreaResponseDto> findAll() {
        List<SmokeArea> areas = smokeAreaRepository.findAll();
        return areas.stream()
                .map(SmokeAreaResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<SmokeAreaResponseDto> findByArea(long api) {
        List<SmokeArea> areas = smokeAreaRepository.findByAreaNumber(api);
        return areas.stream()
                .map(SmokeAreaResponseDto::new)
                .collect(Collectors.toList());
    }
}
