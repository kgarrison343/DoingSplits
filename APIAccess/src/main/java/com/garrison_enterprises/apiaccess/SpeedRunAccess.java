package com.garrison_enterprises.apiaccess;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
import retrofit2.http.Query;


public class SpeedRunAccess {
    @SuppressWarnings("FieldCanBeLocal")
    private final String BaseUrl = "https://www.speedrun.com/api/v1/";

    private final SpeedRun speedRun;

    private interface SpeedRun{
        @GET("games")
        Call<GamesData> games();

        @GET("games")
        Call<GamesData> games(@Query("name") String title);

        @GET("platforms/{id}")
        Call<ConsoleData> platforms(@Path("id") String id);
    }

    public SpeedRunAccess() {
        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        this.speedRun = retrofit.create(SpeedRun.class);
    }

    public List<Game> FetchGames(String title){
        try{
            GamesData result = this.speedRun.games(title).execute().body();
            if (result == null || result.data == null) {
                return new ArrayList<>();
            }

            List<Game> convertedGames = new ArrayList<>();

            for (GameInfo game : result.data){
                List<String> consoleNames = new ArrayList<>();

                for (String id : game.platforms) {
                    ConsoleData console = this.speedRun.platforms(id).execute().body();
                    if(console == null){
                        continue;
                    }

                    consoleNames.add(console.data.name);
                }
                convertedGames.add(new Game(game.id, game.names.international, consoleNames));
            }

            return convertedGames;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
