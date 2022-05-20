package com.daily.themoti.smokearea.controller;

import com.daily.themoti.smokearea.dto.SmokeAreaResponseDto;
import com.daily.themoti.smokearea.service.SmokeAreaService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/area")
public class SmokeAreaController {

    private final SmokeAreaService smokeAreaService;

    @GetMapping("/all")
    public List<SmokeAreaResponseDto> findAll(){
        return smokeAreaService.findAll();
    }

    @GetMapping("/{area}")
    public List<SmokeAreaResponseDto> findByArea(@PathVariable long area){
        return smokeAreaService.findByArea(area);
    }
}
