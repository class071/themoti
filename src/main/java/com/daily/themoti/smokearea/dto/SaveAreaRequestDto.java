package com.daily.themoti.smokearea.dto;

import com.daily.themoti.smokearea.domain.SmokeArea;
import lombok.Builder;

public class SaveAreaRequestDto {

    private String name;
    private String longitude; // 경도
    private String latitude; // 위도

    @Builder
    public SaveAreaRequestDto(String longitude, String latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public SmokeArea toEntity(){
        return SmokeArea.builder()
                .longitude(longitude)
                .latitude(latitude)
                .build();
    }
}
