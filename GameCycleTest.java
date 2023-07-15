package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameCycleTest {

    boolean debugLogging = false;

    @BeforeEach
    void printStartSeparator(){
        System.out.println("=================START===================");
        if(debugLogging){
            System.out.println("LIFE ARRAY BEFORE TRANSITION:\n");
        }
    }

    @AfterEach
    void printEndSeparator(){
        if(debugLogging){
            System.out.println("LIFE ARRAY AFTER TRANSITION:\n");
        }
        System.out.println("==================END====================");
    }

    @Test
    void doesTheThing(){
        // make sure env works
        assertTrue(true);
    }

    @Test
    void testACycleDeadWithLT2LivingFriends(){
        boolean[][] lifeArray = {
                {false,false,false},
                {true,false,false},
                {false,false,false},
        };
        // make sure env works
        GameCycle gameCycle = new GameCycle();
        gameCycle.printRaAsDorAAndReturn(lifeArray);
        gameCycle.transition(lifeArray);
        String actual = gameCycle.printRaAsDorAAndReturn(lifeArray);
        // assert matches
        assertEquals(
                "| | | |\n" +
                "| | | |\n" +
                "| | | |\n",
                actual
        );
    }

    @Test
    void testACycleDeadWithLT2LivingFriendsSecondPermutation(){
        // test basic case in the CENTER it should die because not enough living neighbors
        boolean[][] lifeArray = {
                {false,false,false},
                {false,true,false},
                {false,false,false},
        };
        GameCycle gameCycle = new GameCycle();
        gameCycle.printRaAsDorAAndReturn(lifeArray);

        gameCycle.transition(lifeArray);
        String actual = gameCycle.printRaAsDorAAndReturn(lifeArray);
        assertEquals(
        "| | | |\n" +
                "| | | |\n" +
                "| | | |\n",
                actual
        );
    }

    @Test
    void testACycleDeadWith2LivingFriends(){
        // three living friends
        boolean[][] lifeArray = {
                {false,false,false},
                {true,false,false},
                {false,true,false},
        };
        // make sure env works
        GameCycle gameCycle = new GameCycle();
        gameCycle.printRaAsDorAAndReturn(lifeArray);
        gameCycle.transition(lifeArray);
        String actual = gameCycle.printRaAsDorAAndReturn(lifeArray);
        assertEquals(
        "| | | |\n" +
                "| | | |\n" +
                "| | | |\n",
                actual
        );
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
        GameCycle gameCycle = new GameCycle();
        gameCycle.printRaAsDorAAndReturn(lifeArray);
        gameCycle.transition(lifeArray);
        String actual = gameCycle.printRaAsDorAAndReturn(lifeArray);
        assertEquals(
        "| | | |\n" +
                "|■|■|■|\n" +
                "| |■|■|\n",
                actual
        );
    }

    @Test
    void testACycleWithMoreAlive(){
        // three living friends
        // 1, 0 should be DEAD at the end
        boolean[][] lifeArray = {
                {false,false,false,true},
                {true,false,true,false},
                {true,false,true,true},
                {false,true,true,true},
        };
        GameCycle gameCycle = new GameCycle();
        gameCycle.printRaAsDorAAndReturn(lifeArray);
        gameCycle.transition(lifeArray);
        String actual = gameCycle.printRaAsDorAAndReturn(lifeArray);
        assertEquals(
        "| | | |■|\n" +
                "|■| | |■|\n" +
                "|■| | |■|\n" +
                "| |■|■|■|\n",
                actual);
    }

    @Test
    void testACycleWithEvenMoreAlive(){
        boolean[][] lifeArray = {
                {false,false,false,true,true,false,true},
                {true,false,true,false,true,true,true},
                {true,false,true,true,true,true,true},
                {false,true,true,true,false,false,false},
                {true,false,true,true,true,true,true},
                {true,false,true,true,false,false,true},
                {false,true,true,false,true,true,true},
        };
        GameCycle gameCycle = new GameCycle();
        gameCycle.printRaAsDorAAndReturn(lifeArray);
        gameCycle.transition(lifeArray);
        String actual = gameCycle.printRaAsDorAAndReturn(lifeArray);
        assertEquals(
                "| | | |■|■| |■|\n" +
                "|■| | | | | |■|\n" +
                "| |■| | | | |■|\n" +
                "| |■|■| | | | |\n" +
                "| | | |■|■| |■|\n" +
                "|■| |■|■|■| | |\n" +
                "| |■| | | |■| |\n",
                actual);
    }
}
