package com.example.show_time;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Adapter.OnItemClickListner {
    RecyclerView recyclerView;
    ArrayList<Access_data> arrayList;
    RequestQueue requestQueue;
    Adapter adapter;
    public static final String EXTRA_MOVIE_IMAGE = "movie_image";
    public static final String EXTRA_MOVIE_NAME = "movie_name";
    public static final String EXTRA_MOVIE_INFO = "movie_info";
    public static final String EXTRA_MOVIE_GENRE = "movie_genre";
    public static final String EXTRA_MOVIE_RATING = "movie_rating";
    public static final String EXTRA_MOVIE_TRAILOR = "movie_trailor";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        //first find recycler view
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //make the arraylist of type of data
        arrayList = new ArrayList<>();

        //make volley object to call the process
        //requestQueue = Volley.newRequestQueue(this);
        requestQueue=Singleton_class.getInstance(this).getRequestqueue();



                parsejson();





    }

    public void parsejson() {
        //this url contain the json data
        String url = "https://api.myjson.com/bins/ez9ug";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray array = response.getJSONArray("movies");
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);
                        String movie_name = object.getString("movie_name");
                        String movie_image = object.getString("movie_image");
                        String movie_info= object.getString("movie_info");
                        String movie_genre=object.getString("movie_genre");
                        String movie_rating=object.getString("movie_rating");
                        String  movie_trailor=object.getString("movie_trailor");
                        //add data to arraylist
                        arrayList.add(new Access_data(movie_name, movie_image,movie_info,movie_genre,movie_rating,movie_trailor));


                    }
                    //create the object of adapter here
                    adapter = new Adapter(MainActivity.this, arrayList);

                    //set the recycler adapter
                    recyclerView.setAdapter(adapter);

                    //set the onclicklister

                    adapter.setOnClickListner(MainActivity.this);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //this gets executed when internet permission is not mentioned in android manifest file
                Toast.makeText(MainActivity.this, "Ooops error", Toast.LENGTH_SHORT).show();


            }
        });


        //add the object of json
        requestQueue.add(jsonObjectRequest);

    }

    @Override
    public void onItemclick(int position,View view) {

        Intent intent = new Intent(this, Onclick_activity.class);
        Access_data value= arrayList.get(position);

        intent.putExtra(EXTRA_MOVIE_IMAGE,value.getMovie_image_url() );
        intent.putExtra(EXTRA_MOVIE_NAME,value.getMovie_name() );
        intent.putExtra(EXTRA_MOVIE_INFO,value.getMovie_info() );
        intent.putExtra(EXTRA_MOVIE_GENRE,value.getMovie_genre() );
        intent.putExtra(EXTRA_MOVIE_RATING,value.getMovie_rating() );
        intent.putExtra(EXTRA_MOVIE_TRAILOR,value.getMovie_trailor() );


        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(MainActivity.this, view, "transition");
        startActivity(intent, options.toBundle());




        //startActivity(intent);
    }

}
