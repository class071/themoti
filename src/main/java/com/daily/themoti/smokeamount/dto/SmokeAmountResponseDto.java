package com.daily.themoti.smokeamount.dto;

import com.daily.themoti.smokeamount.domain.SmokeAmount;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SmokeAmountResponseDto {

    private Long userId;

    private int year;

    private int month;

    private int day;

    private LocalDateTime createdDate;

    public SmokeAmountResponseDto(SmokeAmount smokeAmount) {
        this.userId = smokeAmount.getUserId();
        this.year = smokeAmount.getYear();
        this.month = smokeAmount.getMonth();
        this.day = smokeAmount.getDay();
        this.createdDate = smokeAmount.getCreatedDate();
    }
}
