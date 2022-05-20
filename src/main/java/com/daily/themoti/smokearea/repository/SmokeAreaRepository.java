package com.daily.themoti.smokearea.repository;

import com.daily.themoti.smokearea.domain.SmokeArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SmokeAreaRepository extends JpaRepository<SmokeArea, Long> {

    List<SmokeArea> findByArea(long area);
}
