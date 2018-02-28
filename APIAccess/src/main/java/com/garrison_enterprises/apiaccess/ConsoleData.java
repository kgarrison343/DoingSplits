package com.garrison_enterprises.apiaccess;
/*
 * Created by Kyle Garrison on 2/27/2018.
 */

import java.util.List;

class ConsoleData {
    public final List<ConsoleInfo> data;

    ConsoleData(List<ConsoleInfo> data){
        this.data = data;
    }
}

class ConsoleInfo {
    public final String id;
    public final String name;

    ConsoleInfo(String id, String name) {
        this.id = id;
        this.name = name;
    }
}