package kgarrison.doingsplits;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.garrison_enterprises.apiaccess.JsonModels.CategoryData;
import com.garrison_enterprises.apiaccess.JsonModels.CategoryInfo;
import com.garrison_enterprises.apiaccess.SpeedRunAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import models.RunCategory;

public class ViewCategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_categories);
        final String passedGameId = this.getIntent().getStringExtra("SELECTED_GAME_ID");
        ListView categoriesList = (ListView)findViewById(R.id.categoriesList);
        try {
            List<RunCategory> categories = new FetchCategoriesTask().execute(passedGameId).get();
            categoriesList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        categoriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RunCategory selectedCategory = (RunCategory)parent.getAdapter().getItem(position);

                Intent intent = new Intent(getBaseContext(), LeaderboardActivity.class);
                intent.putExtra("gameId", passedGameId);
                intent.putExtra("categoryId", selectedCategory.id);
                startActivity(intent);
            }
        });
    }

    static class FetchCategoriesTask extends AsyncTask<String, Void, List<RunCategory>>{

        @Override
        protected List<RunCategory> doInBackground(String... strings) {
            String gameId = strings[0];
            CategoryData categories = new SpeedRunAccess().FetchCategories(gameId);

            List<RunCategory> results = new ArrayList<>();

            for (CategoryInfo info : categories.data){
                results.add(new RunCategory(info));
            }
            return results;
        }
    }
}
