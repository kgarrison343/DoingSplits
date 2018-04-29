package kgarrison.doingsplits;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.garrison_enterprises.apiaccess.JsonModels.CategoryData;
import com.garrison_enterprises.apiaccess.JsonModels.CategoryInfo;

import java.util.ArrayList;
import java.util.List;

import adapters.LeaderboardsAdapter;
import kgarrison.doingsplits.viewModels.LeaderboardContent;
import loaders.CategoryLoader;
import models.RunCategory;

/**
 * An activity representing a list of Categories. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link LeaderboardDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class LeaderboardListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<RunCategory>> {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private LeaderboardsAdapter mAdapter;

    private String gameId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_list);

        gameId = this.getIntent().getStringExtra("SELECTED_GAME_ID");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (findViewById(R.id.leaderboard_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.leaderboard_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        //Setup loader and use that instead of LeaderboardContent.ITEMS
        getLoaderManager().initLoader(0, null, this);
        mAdapter = new LeaderboardsAdapter(new ArrayList<RunCategory>());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public Loader<List<RunCategory>> onCreateLoader(int id, Bundle args) {
        return new CategoryLoader(this, this.gameId);
    }

    @Override
    public void onLoadFinished(Loader<List<RunCategory>> loader, List<RunCategory> data) {
        mAdapter.setCategories(data);
    }

    @Override
    public void onLoaderReset(Loader<List<RunCategory>> loader) {
        mAdapter.setCategories(new ArrayList<RunCategory>());
    }
}
