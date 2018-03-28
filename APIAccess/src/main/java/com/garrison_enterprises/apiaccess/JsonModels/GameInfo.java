package com.garrison_enterprises.apiaccess.JsonModels;
/*
 * Created by Kyle Garrison on 3/26/2018.
 */

public class GameInfo {
    public final String id;
    public final Names names;
    public final String abbreviation;
    public final String[] platforms;

    public GameInfo(String id, Names names, String abbreviation, String[] platforms){
        this.id = id;
        this.names = names;
        this.abbreviation = abbreviation;
        this.platforms = platforms;
    }
}
