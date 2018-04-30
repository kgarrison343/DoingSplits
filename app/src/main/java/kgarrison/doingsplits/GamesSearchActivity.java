package kgarrison.doingsplits;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
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

import loaders.GameLoader;
import models.Game;

public class GamesSearchActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Game>> {

    ArrayAdapter<Game> mAdapter;
    String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView lv = (ListView) findViewById(R.id.games);
        List<Game> games = new ArrayList<>();

        mAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                games
        );

        lv.setAdapter(mAdapter);

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
        getLoaderManager().initLoader(0, null, this);
    }

    private void openCategories(Game game){
        Intent intent = new Intent(this, LeaderboardListActivity.class);
        intent.putExtra("SELECTED_GAME_ID", game.get_id());
        startActivity(intent);
    }

    private void getNewGame(String title) {
        mTitle = title;
        getLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public Loader<List<Game>> onCreateLoader(int id, Bundle args) {
        return new GameLoader(this, mTitle);
    }

    @Override
    public void onLoadFinished(Loader<List<Game>> loader, List<Game> data) {
        mAdapter.clear();
        mAdapter.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<Game>> loader) {
        mAdapter.clear();
        mAdapter.notifyDataSetChanged();
    }
}
