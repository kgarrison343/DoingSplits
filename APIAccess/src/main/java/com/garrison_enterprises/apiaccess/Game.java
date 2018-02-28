package com.garrison_enterprises.apiaccess;
/*
 * Created by Kyle Garrison on 2/18/2018.
 */

import java.util.List;

public class Game {
    public final String id;
    public final String title;
    public final List<String> consoleName;

    public Game(String id, String title, List<String> consoleName){
        this.id = id;
        this.title = title;
        this.consoleName = consoleName;
    }
}
