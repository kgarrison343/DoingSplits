package kgarrison.doingsplits;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

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
                getNewGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openSplit(View view){
        Intent intent = new Intent(this, SplitActivity.class);

        startActivity(intent);
    }

    public void getNewGame(){

    }
}
