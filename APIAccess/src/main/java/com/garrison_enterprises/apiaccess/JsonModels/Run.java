package com.garrison_enterprises.apiaccess.JsonModels;
/*
 * Created by Kyle Garrison on 3/18/2018.
 */

import java.util.Date;

public class Run {
    public final String id;
    public final Date date;
    public final Date submitted;
    public final RunTimes times;

    Run(String id, Date date, Date submitted, RunTimes times) {
        this.id = id;
        this.date = date;
        this.submitted = submitted;
        this.times = times;
    }
}
