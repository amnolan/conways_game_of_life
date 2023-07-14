package com.company;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameCycleTest {

    @Test
    void doesTheThing(){
        // make sure env works
        assertTrue(true);
    }

    @Test
    void testACycleDeadWithLT2LivingFriends(){
        // three living friends
        // 1, 0 should be DEAD at the end
        boolean[][] lifeArray = {
                {false,false,false},
                {true,false,false},
                {false,false,false},
        };
        System.out.println("LIFE ARRAY BEFORE TRANSITION:");
        // make sure env works
        GameCycle gameCycle = new GameCycle();
        boolean [][] modifiedList = gameCycle.transition(lifeArray);
        System.out.println("LIFE ARRAY AFTER TRANSITION:");
        System.out.println(Arrays.deepToString(modifiedList));
        // assert it is DEAD
        assertFalse(lifeArray[1][0]);
    }

    @Test
    void testACycleDeadWithLT2LivingFriendsSecondPermutation(){
        // test basic case in the CENTER it should die because not enough living neighbors
        boolean[][] lifeArray = {
                {false,false,false},
                {false,true,false},
                {false,false,false},
        };
        System.out.println("LIFE ARRAY BEFORE TRANSITION:");
        // make sure env works
        GameCycle gameCycle = new GameCycle();
        boolean [][] modifiedList = gameCycle.transition(lifeArray);
        System.out.println("LIFE ARRAY AFTER TRANSITION:");
        System.out.println(Arrays.deepToString(modifiedList));
        // assert it is DEAD
        assertFalse(lifeArray[1][1]);
    }

    @Test
    void testACycleDeadWith3LivingFriends(){
        // three living friends
        // 1, 1 should be ALIVE at the end because living neighbors are exactly 3
        boolean[][] lifeArray = {
                {false,false,false},
                {true,false,true},
                {false,true,false},
        };
        System.out.println("LIFE ARRAY BEFORE TRANSITION:");
        // make sure env works
        GameCycle gameCycle = new GameCycle();
        boolean [][] modifiedList = gameCycle.transition(lifeArray);
        System.out.println("LIFE ARRAY AFTER TRANSITION:");
        System.out.println(Arrays.deepToString(modifiedList));
        // assert it is ALIVE
        assertTrue(lifeArray[1][1]);
    }

}
