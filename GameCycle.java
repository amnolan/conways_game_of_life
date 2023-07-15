package com.company;

import java.util.ArrayList;

public class GameCycle {

    boolean debug = false;

    public GameCycle(){

    }

    public GameCycle(boolean debug){
        this.debug = debug;
    }

    public ArrayList<Pair> calculateNeigbors(int height, int width, int currentLocI, int currentLocJ){

        ArrayList<Pair> neighborTuples = new ArrayList<>();

        int topRow = currentLocI - 1;
        topRow = (Math.max(topRow, 0));
        int bottomRow = currentLocI + 1;
        bottomRow = (Math.min(bottomRow, height-1));

        int farLeft = currentLocJ - 1;
        farLeft = (Math.max(farLeft, 0));
        int farRight = currentLocJ + 1;
        farRight = (Math.min(width-1, farRight));

        for(int i = topRow; i <= bottomRow; i++){

            for(int j = farLeft; j <= farRight; j++ ){
                // don't add current location
                if(i != currentLocI || j != currentLocJ){
                    Pair pair = new Pair(i,j);
                    neighborTuples.add(pair);
                }
            }

        }
        if(debug){
            System.out.println("\nneighbor tuples, excluding self:");
            System.out.println("\n" + "i: " + currentLocI + ", j: " + currentLocJ);
            for(int i = 0; i < neighborTuples.size(); i++){
                System.out.print(neighborTuples.get(i));
            }
            System.out.println();
        }
        return neighborTuples;
    }

    public int getLiveNeighbors(boolean[][] oldState, ArrayList<Pair> neighbors){
        int livingNeighbors = 0;
        for(Pair pair : neighbors){
            if(oldState[pair.getJ()][pair.getI()]){
                livingNeighbors += 1;
            }
        }
        return livingNeighbors;
    }

    public boolean[][] transition(boolean [][] oldState){

        int height = oldState.length;
        int width = oldState[1].length;

        if(debug){
            System.out.println("\nCurr height:" + height);
            System.out.println("Curr width:" + width);
        }

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

    public String printRaAsDorAAndReturn(boolean [][] raToPrint){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < raToPrint.length; i++){
            for(int j = 0; j < raToPrint[i].length; j++){
                stringBuilder.append("|");
                stringBuilder.append((raToPrint[i][j]) ? "■" : " ");
                if(j == raToPrint[i].length - 1){
                    stringBuilder.append("|\n");
                }
            }
        }
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }
}
