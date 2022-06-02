package com.daily.themoti.smokeamount.service;

import com.daily.themoti.smokeamount.dto.SmokeAmountRequestDto;
import com.daily.themoti.smokeamount.dto.SmokeAmountResponseDto;

import java.util.List;

public interface SmokeAmountService {

    SmokeAmountResponseDto create(SmokeAmountRequestDto smokeAmountRequestDto);

    List<SmokeAmountResponseDto> findByDay(Long userId, int year, int month, int day);

    List<SmokeAmountResponseDto> findByMonth(Long userId, int year, int month);

    List<SmokeAmountResponseDto> findByYear(Long userId, int year);

    List<SmokeAmountResponseDto> findByUserId(Long userId);
}
