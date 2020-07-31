package com.github.sheiy.redisexample;

import com.github.sheiy.redisexample.LeaderBoard.Leaderboard;
import com.github.sheiy.redisexample.LeaderBoard.LeaderboardFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class SimpleLeaderboardTest {

    @Autowired
    private LeaderboardFactory leaderboardFactory;

    @Test
    void contextLoads() {
        final Leaderboard example = leaderboardFactory.getLeaderboard(UUID.randomUUID().toString());
        example.increment("大狗",1);
        example.increment("大狗",1);
        example.increment("大狗",1);

        example.increment("小狗",1);
        example.increment("小狗",1);
        example.increment("小狗",1);
        example.increment("小狗",1);

        example.increment("中狗",1);

        //小狗 大狗 中狗
        System.out.println("大狗是第："+ example.rank("大狗"));
        System.out.println("小狗是第："+ example.rank("小狗"));
        System.out.println("中狗是第："+ example.rank("中狗"));

        System.out.println("第一到二名依次是： "+example.range(1L,2L));

        System.out.println("第一到最后一名依次是： "+example.all());


    }

}
