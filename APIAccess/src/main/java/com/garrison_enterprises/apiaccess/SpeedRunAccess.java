package com.garrison_enterprises.apiaccess;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;


public class SpeedRunAccess {
    private final String BaseUrl = "https://www.speedrun.com/api/v1/";

    private SpeedRun speedRun;

    private interface SpeedRun{
        @GET("games")
        Call<GamesData> games();

        @GET("games")
        Call<GamesData> games(@Query("name") String title);
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
            if(title.isEmpty()){
                return Arrays.asList(this.speedRun.games().execute().body().data);
            }
            else {
                return Arrays.asList(this.speedRun.games(title).execute().body().data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
