package loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.garrison_enterprises.apiaccess.JsonModels.CategoryData;
import com.garrison_enterprises.apiaccess.JsonModels.CategoryInfo;
import com.garrison_enterprises.apiaccess.SpeedRunAccess;

import java.util.ArrayList;
import java.util.List;

import models.RunCategory;

/*
 * Created by Kyle Garrison on 4/28/2018.
 */
public class CategoryLoader extends AsyncTaskLoader<List<RunCategory>> {
    String gameId;

    public CategoryLoader(Context context, String gameId) {
        super(context);

        if (this.gameId != gameId) {
            this.gameId = gameId;
            onContentChanged();
        }
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged()) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    public List<RunCategory> loadInBackground() {
        CategoryData data = new SpeedRunAccess().FetchCategories(gameId);

        List<RunCategory> categories = new ArrayList<>();

        for (CategoryInfo info : data.data) {
            categories.add(new RunCategory(info));
        }

        return categories;
    }
}
