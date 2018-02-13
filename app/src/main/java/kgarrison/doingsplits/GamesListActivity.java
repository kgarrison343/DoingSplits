package kgarrison.doingsplits;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        ListView lv = (ListView) findViewById(R.id.games);
        List<Game> games = new ArrayList<Game>();
        games.add(new Game("Greendog: The Beached Surfer Dude!", "Sega Genesis"));
        games.add(new Game("VVVVVV", "PC"));

        ArrayAdapter<Game> gameAdapter = new ArrayAdapter<Game>(
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

    public void openSplit(View view){
        Intent intent = new Intent(this, SplitActivity.class);

        startActivity(intent);
    }
}
