package edu.wmdd.finalterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TitlesActivity extends AppCompatActivity {

    private String API_URL = "https://www.reddit.com/r/{SUBREDDIT}/.json";
    private ListView redditListView;
    ArrayList<RedditItem> itemsArrayList;
    private RedditListAdapter redditListAdapter;
    private String subreddit = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titles);

        Intent intent = getIntent();
        this.subreddit = intent.getStringExtra("subreddit");

        redditListView = findViewById(R.id.reddit_list);

        getData();

    }

    private void getData() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL.replace("{SUBREDDIT}", this.subreddit),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject obj = new JSONObject(response);
//                            Log.d("Volley", obj.toString());
//                            Log.d("Volley", obj.getJSONObject("data").toString());
//                            Log.d("Volley", obj.getJSONObject("data").getJSONArray("children").toString());

                            itemsArrayList = new ArrayList<>();
                            JSONArray dataArray  = obj.getJSONObject("data").getJSONArray("children");

                            for (int i = 0; i < dataArray.length(); i++) {

                                RedditItem redditItem = new RedditItem();
                                JSONObject dataobj = dataArray.getJSONObject(i).getJSONObject("data");

                                redditItem.setTitle(dataobj.getString("title"));

                                itemsArrayList.add(redditItem);

                            }

                            setupListview();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void setupListview(){
        redditListAdapter = new RedditListAdapter(this, itemsArrayList);
        redditListView.setAdapter(redditListAdapter);
    }

}
