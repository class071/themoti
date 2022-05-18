package com.daily.themoti.smokearea;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AddressApi {

    KWANGJIN("영업소소재지(도로 명)");

    private final String storedAt;
}
