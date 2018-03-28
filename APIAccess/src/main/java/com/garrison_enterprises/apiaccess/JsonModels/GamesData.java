package com.garrison_enterprises.apiaccess.JsonModels;
/*
 * Created by Kyle Garrison on 2/19/2018.
 */

import java.util.List;

public class GamesData {
    public final List<GameInfo> data;

    GamesData(List<GameInfo> data) {
        this.data = data;
    }
}

