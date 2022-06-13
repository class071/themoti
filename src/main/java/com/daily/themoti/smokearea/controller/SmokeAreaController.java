package com.daily.themoti.smokearea.controller;

import com.daily.themoti.global.api.ApiResponse;
import com.daily.themoti.smokearea.dto.SaveAreaRequestDto;
import com.daily.themoti.smokearea.dto.SmokeAreaResponseDto;
import com.daily.themoti.smokearea.service.SmokeAreaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/area")
public class SmokeAreaController {

    private final SmokeAreaService smokeAreaService;

    @GetMapping("/all")
    public ApiResponse<List<SmokeAreaResponseDto>> findAll() {
        List<SmokeAreaResponseDto> dtos = smokeAreaService.findAll();
        return ApiResponse.success(HttpStatus.OK, dtos);
    }

    @GetMapping("/{area}")
    public ApiResponse<List<SmokeAreaResponseDto>> findByArea(@PathVariable long area){
        List<SmokeAreaResponseDto> dtos = smokeAreaService.findByArea(area);
        return ApiResponse.success(HttpStatus.OK, dtos);
    }

    @PostMapping("/add")
    public ApiResponse<SmokeAreaResponseDto> addArea(@Valid @RequestBody SaveAreaRequestDto saveAreaRequestDto) {
        SmokeAreaResponseDto smokeAreaResponseDto = smokeAreaService.addArea(saveAreaRequestDto);
        return ApiResponse.success(HttpStatus.CREATED, smokeAreaResponseDto);
    }
}
