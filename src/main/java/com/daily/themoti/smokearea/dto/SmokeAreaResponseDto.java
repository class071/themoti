package com.daily.themoti.smokearea.dto;

import com.daily.themoti.smokearea.domain.SmokeArea;
import lombok.Getter;

@Getter
public class SmokeAreaResponseDto {

    private String longitude; // 경도
    private String latitude; // 위도
    private long areaNumber;

    public SmokeAreaResponseDto(SmokeArea smokeArea){
        this.longitude = smokeArea.getLongitude();
        this.latitude = smokeArea.getLatitude();
        this.areaNumber = smokeArea.getAreaNumber();
    }
}
