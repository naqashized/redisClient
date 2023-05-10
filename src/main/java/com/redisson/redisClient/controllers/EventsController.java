package com.redisson.redisClient.controllers;

import com.redisson.redisClient.dtos.Event;
import com.redisson.redisClient.dtos.MetreReadingStatistics;
import com.redisson.redisClient.dtos.Response;
import com.redisson.redisClient.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/v1/events")
@RequiredArgsConstructor
public class EventsController {
    private final EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Response> addEvent(@RequestBody List<Event> events) {
        Mono<Void> response = eventService.addEvents(events);
        return Mono.just(new Response("Events added successfully."));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<MetreReadingStatistics> findEventsStats() {
        return  eventService.findMetreReadingStats();
    }
}
