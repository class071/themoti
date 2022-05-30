package com.daily.themoti.smokeamount.domain;

import com.daily.themoti.community.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class SmokeAmount extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private int month;

    @Column(nullable = false)
    private int day;

    @Column(nullable = false)
    private Long userId;

    @Builder
    public SmokeAmount(Long userId) {
        this.year = LocalDateTime.now().getYear();
        this.month = LocalDateTime.now().getMonthValue();
        this.day = LocalDateTime.now().getDayOfMonth();
        this.userId = userId;
    }
}
