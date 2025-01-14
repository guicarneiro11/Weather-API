package com.guicarneirodev.weatherapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "weather_data")
@Getter
@ToString(exclude = {"favoriteCity"})
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private WeatherInfo info;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "favorite_city_id")
    private FavoriteCity favoriteCity;

    public WeatherData(WeatherInfo info, FavoriteCity favoriteCity) {
        this.info = Objects.requireNonNull(info, "Weather info cannot be null");
        this.favoriteCity = Objects.requireNonNull(favoriteCity, "Favorite city cannot be null");
    }
}
