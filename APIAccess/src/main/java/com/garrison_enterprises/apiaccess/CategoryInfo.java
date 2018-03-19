package com.garrison_enterprises.apiaccess;
/*
 * Created by Kyle Garrison on 3/18/2018.
 */

public class CategoryInfo{
    public final String id;
    public final String name;
    public final String rules;

    CategoryInfo(String id, String name, String rules) {
        this.id = id;
        this.name = name;
        this.rules = rules;
    }
}
