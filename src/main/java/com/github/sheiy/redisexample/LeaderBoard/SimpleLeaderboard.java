package com.github.sheiy.redisexample.LeaderBoard;

import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

/**
 * ZSet实现，排行榜
 */
class SimpleLeaderboard implements Leaderboard{

    private final String leaderboardName;

    private final RedisTemplate<String, String> redisTemplate;

    protected SimpleLeaderboard(String leaderboardName, RedisTemplate<String, String> redisTemplate) {
        this.leaderboardName = leaderboardName;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void increment(String member, int value) {
        final BoundZSetOperations<String, String> operations = redisTemplate.boundZSetOps(leaderboardName);
        operations.incrementScore(member, value);
    }

    @Override
    public Long rank(String member) {
        final BoundZSetOperations<String, String> operations = redisTemplate.boundZSetOps(leaderboardName);
        final Long rank = operations.reverseRank(member);
        if (rank == null) {
            return Long.MAX_VALUE;
        } else {
            return rank + 1;
        }
    }

    @Override
    public Set<String> range(Long start, Long end) {
        final BoundZSetOperations<String, String> operations = redisTemplate.boundZSetOps(leaderboardName);
        return operations.reverseRange(start - 1, end - 1);
    }

    @Override
    public Set<String> all() {
        final BoundZSetOperations<String, String> operations = redisTemplate.boundZSetOps(leaderboardName);
        return operations.reverseRange(0, Long.MAX_VALUE);
    }

    @Override
    public Long score(String member) {
        final Double score = redisTemplate.boundZSetOps(leaderboardName).score(member);
        if (score == null) {
            return 0L;
        }
        return score.longValue();
    }

    @Override
    public String leaderboardName() {
        return leaderboardName;
    }
}
