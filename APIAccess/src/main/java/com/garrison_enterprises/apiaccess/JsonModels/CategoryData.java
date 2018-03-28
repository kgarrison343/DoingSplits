package com.garrison_enterprises.apiaccess.JsonModels;
/*
 * Created by Kyle Garrison on 3/18/2018.
 */

import java.util.List;

public class CategoryData {
    public final List<CategoryInfo> data;

    CategoryData(List<CategoryInfo> data) {
        this.data = data;
    }
}

