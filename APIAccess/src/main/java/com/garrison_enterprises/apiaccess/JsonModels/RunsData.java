package com.garrison_enterprises.apiaccess.JsonModels;
/*
 * Created by Kyle Garrison on 3/18/2018.
 */

import java.util.List;

public class RunsData {
    public final List<RunData> runs;
    RunsData(List<RunData> runs) {
        this.runs = runs;
    }

}
