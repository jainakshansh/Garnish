package me.akshanshjain.garnish;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

import me.akshanshjain.garnish.Fragments.StepsListFragment;
import me.akshanshjain.garnish.Objects.StepsItem;

public class StepDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ArrayList<StepsItem> stepsItemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);

        //Setting up the toolbar activity.
        toolbar = findViewById(R.id.toolbar_step_detail);
        setSupportActionBar(toolbar);

        //Using a fragment manager and transaction to add fragment to the screen.
        FragmentManager fragmentManager = getSupportFragmentManager();

        //Fragment transaction.
        fragmentManager.beginTransaction()
                //TODO Need to add the fragment to be committed.
                .commit();
    }
}
