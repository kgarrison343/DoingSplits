package loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.garrison_enterprises.apiaccess.SpeedRunAccess;

import java.util.ArrayList;
import java.util.List;

import models.Game;

/*
 * Created by Kyle Garrison on 4/29/2018.
 */
public class GameLoader extends AsyncTaskLoader<List<Game>> {
    String searchString;

    public GameLoader(Context context, String searchString) {
        super(context);
        this.searchString = searchString;
        onContentChanged();
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
    public List<Game> loadInBackground() {
        List<Game> games = new ArrayList<>();
        if (this.searchString == null || this.searchString.isEmpty()) {
            return games;
        }
        SpeedRunAccess access = new SpeedRunAccess();
        List<com.garrison_enterprises.apiaccess.JsonModels.Game> data = access.FetchGames(searchString);

        for (com.garrison_enterprises.apiaccess.JsonModels.Game game : data) {
            games.add(new Game(game));
        }

        return games;
    }
}
