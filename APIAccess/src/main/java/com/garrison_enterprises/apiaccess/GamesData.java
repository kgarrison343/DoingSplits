package com.garrison_enterprises.apiaccess;
/*
 * Created by Kyle Garrison on 2/19/2018.
 */

import java.util.List;

class GamesData {
    public final List<GameInfo> data;

    GamesData(List<GameInfo> data) {
        this.data = data;
    }
}

class GameInfo {
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