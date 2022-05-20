package com.daily.themoti.smokearea.service;

import com.daily.themoti.smokearea.dto.SmokeAreaResponseDto;

import java.util.List;

public interface SmokeAreaService {

    List<SmokeAreaResponseDto> findAll();
    List<SmokeAreaResponseDto> findByArea(long api);
}
