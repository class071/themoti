package com.daily.themoti.smokeamount.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SmokeAmountRequestDto {

    @NotNull
    private Long userId;
}
