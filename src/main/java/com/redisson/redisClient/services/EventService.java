package com.redisson.redisClient.services;

import com.redisson.redisClient.dtos.Event;
import com.redisson.redisClient.dtos.MetreReadingStatistics;
import reactor.core.publisher.Mono;

import java.util.List;

public interface EventService {
    Mono<Void> addEvents(List<Event> events);
    Mono<MetreReadingStatistics> findMetreReadingStats();
}
