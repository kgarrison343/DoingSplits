package com.garrison_enterprises.apiaccess.JsonModels;
/*
 * Created by Kyle Garrison on 3/25/2018.
 */

class RunTimes {
    public final String primary;
    public final double primary_t;
    public final String realtime;
    public final double realtime_t;
    public final String realtime_noloads;
    public final double realtime_noloads_t;
    public final String ingame;
    public final double ingame_t;

    RunTimes(String primary, double primary_t, String realtime, double realtime_t, String realtime_noloads, double realtime_noloads_t, String ingame, double ingame_t) {
        this.primary = primary;
        this.primary_t = primary_t;
        this.realtime = realtime;
        this.realtime_t = realtime_t;
        this.realtime_noloads = realtime_noloads;
        this.realtime_noloads_t = realtime_noloads_t;
        this.ingame = ingame;
        this.ingame_t = ingame_t;
    }
}
