package me.akshanshjain.garnish;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

import me.akshanshjain.garnish.Fragments.StepDetailFragment;
import me.akshanshjain.garnish.Fragments.StepsListFragment;
import me.akshanshjain.garnish.Objects.StepsItem;

public class StepDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private static final String STEPS_KEY = "STEPSINFO";
    private ArrayList<StepsItem> stepsItemArrayList;

    private static final String CLICKED_POSITION = "CLICKEDPOSITION";
    private int clickedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);

        /*
        Getting the list and name from the Recipe Detail Activity.
        */
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            stepsItemArrayList = intent.getParcelableArrayListExtra(STEPS_KEY);
            clickedPosition = intent.getIntExtra(CLICKED_POSITION, 0);
        }

        //Setting up the toolbar activity.
        toolbar = findViewById(R.id.toolbar_step_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(stepsItemArrayList.get(clickedPosition).getShortDesc());

        //Initiating the fragment to be added to the layout.
        StepDetailFragment stepDetailFragment = new StepDetailFragment();

        //Using a fragment manager and transaction to add fragment to the screen.
        FragmentManager fragmentManager = getSupportFragmentManager();

        //Fragment transaction.
        fragmentManager.beginTransaction()
                .add(R.id.step_detail_frame_layout, stepDetailFragment)
                .commit();
    }
}
