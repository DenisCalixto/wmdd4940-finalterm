package edu.wmdd.finalterm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btAdd;
    static ArrayList<SubredditItem> itemsArrayList = new ArrayList<SubredditItem>();
    private ListView subredditListView;
    private SubredditListAdapter subredditListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SubredditItem item1 = new SubredditItem();
        item1.name = "pics";
        itemsArrayList.add(item1);
        SubredditItem item2 = new SubredditItem();
        item2.name = "worldnews";
        itemsArrayList.add(item2);

        subredditListView = findViewById(R.id.subredditListView);
        subredditListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3)
            {
                SubredditItem item = (SubredditItem) adapter.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, TitlesActivity.class);
                intent.putExtra("subreddit", item.name);
                startActivity(intent);
            }
        });

        subredditListAdapter = new SubredditListAdapter(this, itemsArrayList);
        subredditListView.setAdapter(subredditListAdapter);

        btAdd = (Button) findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
