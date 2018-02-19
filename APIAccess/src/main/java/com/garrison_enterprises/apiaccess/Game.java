package com.garrison_enterprises.apiaccess;
/*
 * Created by Kyle Garrison on 2/18/2018.
 */

public class Game {
    //TODO: Implement class to hold data from api calls
    public final String id;
    public final Names names;
    public final String abbreviation;

    public Game(String id, Names names, String abbreviation){
        this.id = id;
        this.names = names;
        this.abbreviation = abbreviation;
    }
}
