package kgarrison.doingsplits;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.garrison_enterprises.apiaccess.CategoryData;
import com.garrison_enterprises.apiaccess.CategoryInfo;
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
        String passedGameId = this.getIntent().getStringExtra("SELECTED_GAME_ID");
        ListView categoriesList = (ListView)findViewById(R.id.categoriesList);
        try {
            List<RunCategory> categories = new FetchCategoriesTask().execute(passedGameId).get();
            categoriesList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

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
