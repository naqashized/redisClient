package com.redisson.redisClient.dtos;

public record MetreReadingStatistics(double average, double highest, double lowest, long total) {
}
