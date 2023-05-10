package com.redisson.redisClient.dtos;

import java.io.Serializable;

public record Event(long timestamp, double metreReading) implements Serializable {
}
