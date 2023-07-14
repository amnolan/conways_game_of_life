package com.company;

import java.util.ArrayList;

public class GameCycle {

    public ArrayList<Pair> calculateNeigbors(int height, int width, int currentLocI, int currentLocJ){

        ArrayList<Pair> neighborTuples = new ArrayList<>();

        int topRow = currentLocI - 1;
        int bottomRow = currentLocI + 1;

        int farLeft = currentLocJ - 1;
        int farRight = currentLocJ + 1;

        for(int i = topRow; i <= bottomRow; i++){

            for(int j = farLeft; j <= farRight; j++ ){
                // don't allow negatives or out of bounds locations
                if(i >= 0 && i < height && j >= 0 && j < width){
                    // don't add current location
                    if(currentLocI != i || currentLocJ != j){
                        Pair pair = new Pair(i,j);
                        neighborTuples.add(pair);
                    }
                }
            }

        }
        System.out.println("neighbor tuples, excluding self: " + neighborTuples);
        return neighborTuples;
    }

    public int getLiveNeighbors(boolean[][] oldState, ArrayList<Pair> neighbors){
        int livingNeighbors = 0;
        for(Pair pair : neighbors){
            if(oldState[pair.getJ()][pair.getI()]){
                livingNeighbors += 1;
            }
            System.out.println();
        }
        return livingNeighbors;
    }

    public boolean[][] transition(boolean [][] oldState){

        int height = oldState.length;
        int width = oldState[1].length;

        System.out.println("Curr height:" + height);
        System.out.println("Curr width:" + width);

        for(int i = 0; i < oldState.length; i++){
            for(int j = 0; j < oldState[i].length; j++){
                ArrayList<Pair> neighbors = calculateNeigbors(height, width, i, j);
                int livingNeighbors = getLiveNeighbors(oldState,neighbors);
                // if the CURRENT cell is LIVING
                if(oldState[i][j]){
                    // if a live cell at current time has LESS THAN 2 live neighbors it dies
                    // if a cell has GREATER THAN 3 live neighbors it dies
                    if(livingNeighbors < 2 || livingNeighbors > 3){
                        oldState[i][j] = false;
                    }
                    // if a live cell has EXACTLY 2 or 3 it survives
                    // EXACTLY 2 or 3 IMPLICITLY SURVIVES (take no action)
                }else{
                    // current cell is dead
                    livingNeighbors = getLiveNeighbors(oldState,neighbors);
                    if(livingNeighbors==3){
                        // any dead cell with EXACTLY 3 live neighbors becomes alive
                        oldState[i][j] = true;
                    }
                }
            }
        }
        // returned modified list
        return oldState;
    }
}
