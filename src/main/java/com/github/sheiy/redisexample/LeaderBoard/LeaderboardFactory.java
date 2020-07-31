package com.github.sheiy.redisexample.LeaderBoard;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class LeaderboardFactory {

    private final RedisTemplate<String, String> redisTemplate;

    private final ConcurrentHashMap<String, Leaderboard> leaderboards = new ConcurrentHashMap<>();

    public Leaderboard getLeaderboard(String leaderboardName) {
        return leaderboards.computeIfAbsent(leaderboardName, key -> new SimpleLeaderboard(key, redisTemplate));
    }

}
