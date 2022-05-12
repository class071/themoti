package com.daily.themoti.smokearea;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class SmokeArea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String longitude; // 경도

    @Column
    private String latitude; // 위도

    @Builder
    public SmokeArea(String longitude, String latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
