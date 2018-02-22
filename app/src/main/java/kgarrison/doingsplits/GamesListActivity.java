package kgarrison.doingsplits;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.garrison_enterprises.apiaccess.SpeedRunAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import models.Game;

public class GamesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_list);
        Toolbar actionBar = (Toolbar) findViewById(R.id.toolbar2);
        this.setSupportActionBar(actionBar);
        actionBar.setTitleTextColor(Color.WHITE);

        ListView lv = (ListView) findViewById(R.id.games);
        List<Game> games = new ArrayList<>();
        games.add(new Game("Greendog: The Beached Surfer Dude!", "Sega Genesis"));
        games.add(new Game("VVVVVV", "PC"));

        ArrayAdapter<Game> gameAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                games
        );

        lv.setAdapter(gameAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openSplit(view);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_add:
                dropTitleTextBox();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openSplit(View view){
        Intent intent = new Intent(this, SplitActivity.class);

        startActivity(intent);
    }

    private void dropTitleTextBox(){
        GridLayout grid = (GridLayout) findViewById(R.id.dropBox);
        Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down_animation);

        slideDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                GridLayout grid = (GridLayout) findViewById(R.id.dropBox);
                grid.setY(grid.getHeight() + grid.getY());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        grid.startAnimation(slideDown);
    }

    private void getNewGame(String title) {

        List<com.garrison_enterprises.apiaccess.Game> games = null;
        try {
            games = new FetchGamesTask().execute(title).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        assert games != null;
        for (com.garrison_enterprises.apiaccess.Game game : games){
            Game newGame = new Game(game.names.international, "");
        }
    }

    private static class FetchGamesTask extends AsyncTask<String, Void, List<com.garrison_enterprises.apiaccess.Game>>{

        @Override
        protected List<com.garrison_enterprises.apiaccess.Game> doInBackground(String... titles) {
            SpeedRunAccess access = new SpeedRunAccess();
            return access.FetchGames(titles[0]);
        }
    }
}
