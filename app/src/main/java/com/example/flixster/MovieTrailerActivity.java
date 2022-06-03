package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import okhttp3.Headers;

public class MovieTrailerActivity extends YouTubeBaseActivity {

    Movie movie;

    String videoId;
    /*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_trailer);
    }

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_trailer);

        String YOUTUBE_API_KEY = "/videos?api_key=<AIzaSyDffOB5qBgO2-_Qv9ODamT_A_9_kD2_pLQ>";
        String API_CALL_STRING = "https://api.themoviedb.org/3/movie/";

        // unwrap the movie passed in via intent, using its simple name as a key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(API_CALL_STRING + movie.getId().toString() + YOUTUBE_API_KEY, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
             //   Log.d(, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    JSONObject jsonObject1 = (JSONObject) results.get(0);

                   videoId = (String) jsonObject1.get("key");

                    Log.i("asdf","Results " + results.toString());
                  //  movies.addAll(Movie.fromJsonArray(results));
                  //  movieAdapter.notifyDataSetChanged();

                //    Log.i(TAG, "Movies: " + movies.size());
                } catch (JSONException e) {
                    Log.e("TAG", "Hit json exception", e);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
               Log.d("TAG", "onFailure");
            }
        });

        // temporary test video id --
     //  final String videoId = results;

        // resolve the player view from the layout

/*
        YouTubePlayerView playerView = (YouTubePlayerView) findViewById(R.id.videoId);

        // initialize with API key stored in secrets.xml
       playerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer youTubePlayer, boolean b) {

                // do any work here to cue video, play video, etc.
                youTubePlayer.cueVideo(videoId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult youTubeInitializationResult) {
                // log the error
                Log.e("MovieTrailerActivity", "Error initializing YouTube player");
            }
        });
        */

        /*

<com.google.android.youtube.player.YouTubePlayerView
    android:id="@+id/player"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>

</androidx.constraintlayout.widget.ConstraintLayout>
         */
    }
}