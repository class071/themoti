package com.daily.themoti.smokearea.service;


import com.daily.themoti.smokearea.dto.SaveAreaRequestDto;
import com.daily.themoti.smokearea.dto.SmokeAreaResponseDto;

public interface SmokeAreaService {

    public SmokeAreaResponseDto save(SaveAreaRequestDto saveAreaRequestDto);

}
