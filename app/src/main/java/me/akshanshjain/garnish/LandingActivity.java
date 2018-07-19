package me.akshanshjain.garnish;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class LandingActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView recipeRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        //Setting up the toolbar for the activity.
        toolbar = findViewById(R.id.toolbar_landing);
        setSupportActionBar(toolbar);

        recipeRecycler = findViewById(R.id.recycler_view_landing);
    }
}
