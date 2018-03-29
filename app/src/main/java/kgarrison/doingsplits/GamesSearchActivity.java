package kgarrison.doingsplits;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.garrison_enterprises.apiaccess.SpeedRunAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import models.Game;

public class GamesSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView lv = (ListView) findViewById(R.id.games);
        List<Game> games = new ArrayList<>();
        //games.add(new Game("Greendog: The Beached Surfer Dude!", "Sega Genesis"));
        //games.add(new Game("VVVVVV", "PC"));

        ArrayAdapter<Game> gameAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                games
        );

        lv.setAdapter(gameAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Game selectedGame = (Game)parent.getAdapter().getItem(position);
                openCategories(selectedGame);
            }
        });

        final TextView searchTitle = (TextView) findViewById(R.id.searchTitle);
        searchTitle.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP){
                    getNewGame(searchTitle.getText().toString());
                    return false;
                }

                return false;
            }
        });
    }

    private void openCategories(Game game){
        Intent intent = new Intent(this, ViewCategoriesActivity.class);
        intent.putExtra("SELECTED_GAME_ID", game.get_id());
        startActivity(intent);
    }

    private void getNewGame(String title) {

        List<com.garrison_enterprises.apiaccess.JsonModels.Game> games = null;
        try {
            games = new FetchGamesTask().execute(title).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        assert games != null;
        ListView lv = (ListView) findViewById(R.id.games);
        List<Game> gamesList = new ArrayList<>();
        for (com.garrison_enterprises.apiaccess.JsonModels.Game game : games){
            Game newGame = new Game(game.title, game.id, game.consoleName);
            gamesList.add(newGame);
        }

        lv.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                gamesList
        ));
    }

    private static class FetchGamesTask extends AsyncTask<String, Void, List<com.garrison_enterprises.apiaccess.JsonModels.Game>> {

        @Override
        protected List<com.garrison_enterprises.apiaccess.JsonModels.Game> doInBackground(String... titles) {
            SpeedRunAccess access = new SpeedRunAccess();
            return access.FetchGames(titles[0]);
        }
    }
}
