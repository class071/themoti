package com.daily.themoti.smokeamount.controller;

import com.daily.themoti.global.api.ApiResponse;
import com.daily.themoti.smokeamount.dto.SmokeAmountRequestDto;
import com.daily.themoti.smokeamount.dto.SmokeAmountResponseDto;
import com.daily.themoti.smokeamount.service.SmokeAmountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class SmokeAmountController {

    private final SmokeAmountService smokeAmountService;

    @PostMapping("/api/smoke-amount")
    public ApiResponse<SmokeAmountResponseDto> create(@Valid @RequestBody SmokeAmountRequestDto smokeAmountRequestDto) {
        SmokeAmountResponseDto smokeAmountResponseDto = smokeAmountService.create(smokeAmountRequestDto);
        return ApiResponse.success(HttpStatus.CREATED, smokeAmountResponseDto);
    }

    @GetMapping("/api/smoke-amount/{userId}")
    public ApiResponse<List<SmokeAmountResponseDto>> findByDay(@PathVariable Long userId,
                                                               @RequestParam int year,
                                                               @RequestParam int month,
                                                               @RequestParam int day) {
        List<SmokeAmountResponseDto> dtos = smokeAmountService.findByDay(userId, year, month, day);
        return ApiResponse.success(HttpStatus.OK, dtos);
    }

    @GetMapping("/api/smoke-amount/month/{userId}")
    public ApiResponse<List<SmokeAmountResponseDto>> findByMonth(@PathVariable Long userId,
                                                                 @RequestParam int year,
                                                                 @RequestParam int month) {
        List<SmokeAmountResponseDto> dtos = smokeAmountService.findByMonth(userId, year, month);
        return ApiResponse.success(HttpStatus.OK, dtos);
    }
}
