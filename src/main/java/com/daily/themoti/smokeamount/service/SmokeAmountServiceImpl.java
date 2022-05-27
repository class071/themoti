package com.daily.themoti.smokeamount.service;

import com.daily.themoti.smokeamount.domain.SmokeAmount;
import com.daily.themoti.smokeamount.dto.SmokeAmountRequestDto;
import com.daily.themoti.smokeamount.dto.SmokeAmountResponseDto;
import com.daily.themoti.smokeamount.repository.SmokeAmountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SmokeAmountServiceImpl implements SmokeAmountService {

    private final SmokeAmountRepository smokeAmountRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public SmokeAmountResponseDto create(SmokeAmountRequestDto smokeAmountRequestDto) {
        SmokeAmount newSmoke = new SmokeAmount(smokeAmountRequestDto.getUserId());
        modelMapper.map(smokeAmountRequestDto, newSmoke);
        smokeAmountRepository.save(newSmoke);
        return new SmokeAmountResponseDto(newSmoke);
    }

    @Override
    public List<SmokeAmountResponseDto> findByDay(Long userId, int year, int month, int day) {
        List<SmokeAmount> amounts = smokeAmountRepository.findByUserIdAndYearAndMonthAndDay(userId, year, month, day);
        return amounts.stream()
                .map(SmokeAmountResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<SmokeAmountResponseDto> findByMonth(Long userId, int year, int month) {
        List<SmokeAmount> amounts = smokeAmountRepository.findByUserIdAndYearAndMonth(userId, year, month);
        return amounts.stream()
                .map(SmokeAmountResponseDto::new)
                .collect(Collectors.toList());
    }
}
