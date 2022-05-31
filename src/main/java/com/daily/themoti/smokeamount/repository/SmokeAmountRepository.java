package com.daily.themoti.smokeamount.repository;

import com.daily.themoti.smokeamount.domain.SmokeAmount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SmokeAmountRepository extends JpaRepository<SmokeAmount, Long> {

    List<SmokeAmount> findByUserIdAndYearAndMonthAndDay(Long userId, int year, int month, int day);

    List<SmokeAmount> findByUserIdAndYearAndMonth(Long userId, int year, int month);
}
