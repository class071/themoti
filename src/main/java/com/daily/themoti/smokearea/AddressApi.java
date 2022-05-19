package com.daily.themoti.smokearea;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AddressApi {

    KWANGJIN("영업소소재지(도로 명)"),
    JOONG("설치도로명주소"),
    JOONGRANG("주소"),
    DONGJAK("주소"),
    SONGPA("도로명주소"),
    SEODAEMOON("설치위치"),
    DONGDAEMOON("설치위치");

    private final String storedAt;
}
