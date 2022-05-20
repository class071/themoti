package com.daily.themoti.smokearea.dto;

import com.daily.themoti.smokearea.domain.SmokeArea;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SaveAreaRequestDto {

    private String longitude; // 경도
    private String latitude; // 위도
    private long areaNumber;

    @Builder
    public SaveAreaRequestDto(String longitude, String latitude, long areaNumber){
        this.longitude = longitude;
        this.latitude = latitude;
        this.areaNumber = areaNumber;
    }

    public SmokeArea toEntity(){
        return SmokeArea.builder()
                .longitude(longitude)
                .latitude(latitude)
                .areaNumber(areaNumber)
                .build();
    }
}
