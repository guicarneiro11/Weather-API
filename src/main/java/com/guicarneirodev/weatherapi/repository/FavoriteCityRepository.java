package com.guicarneirodev.weatherapi.repository;

import com.guicarneirodev.weatherapi.model.FavoriteCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteCityRepository extends JpaRepository<FavoriteCity, Long> {
    List<FavoriteCity> findByUserId(String userId);
    Optional<FavoriteCity> findByCityNameAndUserId(String cityName, String userId);
    boolean existsByCityNameAndUserId(String cityName, String userId);
}
