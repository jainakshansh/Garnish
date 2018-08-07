package me.akshanshjain.garnish;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import me.akshanshjain.garnish.Fragments.StepsListFragment;

public class StepDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);

        //Setting up the toolbar activity.
        toolbar = findViewById(R.id.toolbar_step_detail);
        setSupportActionBar(toolbar);

        //Creating a new StepsListFragment instance.
        StepsListFragment stepsListFragment = new StepsListFragment();

        //Using a fragment manager and transaction to add fragment to the screen.
        FragmentManager fragmentManager = getSupportFragmentManager();

        //Fragment transaction.
        fragmentManager.beginTransaction()
                //TODO Need to add the fragment to be committed.
                .commit();
    }
}
