package models;
/*
 * Created by Kyle Garrison on 3/18/2018.
 */

import com.garrison_enterprises.apiaccess.CategoryInfo;

public class RunCategory {
    public String id;
    public String name;
    public String rules;

    public RunCategory(){

    }

    public RunCategory(CategoryInfo categoryInfo){
        this.id = categoryInfo.id;
        this.name = categoryInfo.name;
        this.rules = categoryInfo.rules;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
