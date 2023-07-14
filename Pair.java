package com.company;

public class Pair {

    Integer i;
    Integer j;

    public Pair(Integer i, Integer j){
        this.i = i;
        this.j = j;
    }

    public int getI(){
        return this.i;
    }

    public int getJ(){
        return this.j;
    }

    public String toString(){
        return "\n{ i : " + this.i + ", j : " + this.j + " }\n";
    }
}
