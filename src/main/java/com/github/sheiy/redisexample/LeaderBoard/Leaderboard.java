package com.github.sheiy.redisexample.LeaderBoard;

import java.util.Set;

/**
 * ZSet实现，排行榜
 */
public interface Leaderboard {

     void increment(String member, int value);

     Long rank(String member);

     Set<String> range(Long start, Long end);

     Set<String> all();

     Long score(String member);

     String leaderboardName();
}
