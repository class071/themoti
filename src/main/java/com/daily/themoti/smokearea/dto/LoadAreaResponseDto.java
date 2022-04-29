package com.daily.themoti.smokearea.dto;

import lombok.Getter;

@Getter
public class LoadAreaResponseDto {

    private String saveResult;

    public LoadAreaResponseDto(boolean isAllSaved){
        this.saveResult = (isAllSaved) ? "All saved" : "Not all saved";
    }
}
