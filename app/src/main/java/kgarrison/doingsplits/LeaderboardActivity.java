package kgarrison.doingsplits;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.garrison_enterprises.apiaccess.JsonModels.LeaderboardData;
import com.garrison_enterprises.apiaccess.JsonModels.RunData;
import com.garrison_enterprises.apiaccess.SpeedRunAccess;

import java.util.concurrent.ExecutionException;

public class LeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        Intent receivedIntent = getIntent();

        String gameId = receivedIntent.getStringExtra("gameId");
        String categoryId = receivedIntent.getStringExtra("categoryId");

        ListView leaderboardList  = (ListView) findViewById(R.id.leaderboardList);

        try {
            LeaderboardData data = new FetchLeaderboardTask().execute(new LeaderboardRequest(gameId, categoryId)).get();
            ArrayAdapter<RunData> runArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data.data.runs);
            leaderboardList.setAdapter(runArrayAdapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class FetchLeaderboardTask extends AsyncTask<LeaderboardRequest, Void, LeaderboardData>{

        @Override
        protected LeaderboardData doInBackground(LeaderboardRequest... requests) {
            LeaderboardRequest request = requests[0];
            return new SpeedRunAccess().FetchLeaderboard(request.gameId, request.categoryId);
        }
    }

    private static class LeaderboardRequest {
        final String categoryId;
        final String gameId;

        private LeaderboardRequest(String gameId, String categoryId) {
            this.categoryId = categoryId;
            this.gameId = gameId;
        }
    }
}
