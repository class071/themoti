package com.daily.themoti.smokearea.controller;

import com.daily.themoti.global.api.ApiResponse;
import com.daily.themoti.global.constant.SuccessCode;
import com.daily.themoti.smokearea.dto.LoadAreaResponseDto;
import com.daily.themoti.smokearea.service.SmokeAreaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/area")
public class SmokeAreaController {

    private final SmokeAreaService smokeAreaService;

}
