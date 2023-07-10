package com.redisson.redisClient.services;

import com.redisson.redisClient.dtos.Event;
import com.redisson.redisClient.dtos.MetreReadingStatistics;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RTimeSeries;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{
    private final RedissonClient redissonClient;
    private static final String CACHE_NAME = "EventData";
    private static final int CACHE_TIME = 60;
    @Override
    public Mono<Void> addEvents(List<Event> events) {
        RTimeSeries<List<Event>, Long> rTimeSeries = redissonClient.getTimeSeries(CACHE_NAME);
        rTimeSeries.add(Instant.now().getEpochSecond(), events, CACHE_TIME, TimeUnit.SECONDS);
        return Mono.empty();
    }

    @Override
    public Mono<MetreReadingStatistics> findMetreReadingStats() {
        RTimeSeries<List<Event>, Long> rTimeSeries = redissonClient.getTimeSeries(CACHE_NAME);
        DoubleSummaryStatistics metreStatistics = rTimeSeries.stream().
                flatMap(Collection::stream).collect(Collectors.summarizingDouble(Event::metreReading));
        return Mono.just(new MetreReadingStatistics(metreStatistics.getAverage(), metreStatistics.getMax(),
                metreStatistics.getMin(), metreStatistics.getCount()));
    }
}
