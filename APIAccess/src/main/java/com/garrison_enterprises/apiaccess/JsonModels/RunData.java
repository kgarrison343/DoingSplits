package com.garrison_enterprises.apiaccess.JsonModels;
/*
 * Created by Kyle Garrison on 3/27/2018.
 */

public class RunData {
    public final Run run;
    public final int place;

    public RunData(Run run, int place) {
        this.run = run;
        this.place = place;
    }

    @Override
    public String toString(){
        return place + ". " + run.times.primary;
    }
}
