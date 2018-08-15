package me.akshanshjain.garnish.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.akshanshjain.garnish.Fragments.StepDetailFragment;
import me.akshanshjain.garnish.Fragments.StepsListFragment;
import me.akshanshjain.garnish.Objects.StepsItem;
import me.akshanshjain.garnish.R;

public class StepListActivity extends AppCompatActivity implements StepsListFragment.OnStepClickListener {

    private static final String STEPS_KEY = "STEPSINFO";
    private static final String RECIPE_NAME = "RECIPENAME";
    private ArrayList<StepsItem> stepsItemArrayList;
    private String recipeName;

    private static final String CLICKED_POSITION = "CLICKEDPOSITION";
    private int clickedPosition;

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_list);

        //Setting up the toolbar for the activity.
        Toolbar toolbar = findViewById(R.id.toolbar_step_list);
        setSupportActionBar(toolbar);

        /*
        Getting the list and name from the Recipe Detail Activity.
        */
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            recipeName = intent.getStringExtra(RECIPE_NAME);
            stepsItemArrayList = intent.getParcelableArrayListExtra(STEPS_KEY);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(recipeName);
        }

        StepsListFragment stepsListFragment = new StepsListFragment();

        //Sending the data to the fragment.
        Bundle arguments = new Bundle();
        arguments.putParcelableArrayList(STEPS_KEY, stepsItemArrayList);
        stepsListFragment.setArguments(arguments);

        //Adding the fragment to the view using FrameLayout.
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.steps_list_frame_layout, stepsListFragment)
                .commit();

        /*
        This conditional statement sets the UI w.r.t. mobile or tablet.
        Creating a flexible layout here.
        Also, sends the required data to be updated into the containers.
        */
        if (findViewById(R.id.two_pane_linear_layout) != null) {
            mTwoPane = true;
            //Initiating the fragment to be added to the layout.
            StepDetailFragment stepDetailFragment = new StepDetailFragment();

            //Sending the data to the fragment for setting into the containers.
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(STEPS_KEY, stepsItemArrayList);
            bundle.putInt(CLICKED_POSITION, clickedPosition);
            stepDetailFragment.setArguments(bundle);

            //Using a fragment manager and transaction to add fragment to the screen.
            FragmentManager fm = getSupportFragmentManager();

            //Fragment transaction.
            fm.beginTransaction()
                    .add(R.id.step_detail_frame_layout, stepDetailFragment)
                    .commit();
        } else {
            mTwoPane = false;
        }
    }

    /*
    Receiving the information from the Step List Fragment.
    Information includes the position of the item clicked in the recycler view.
    */
    @Override
    public void onStepItemClicked(int position) {
        if (mTwoPane) {
            /*
            Updating the fragment in the detail section for two pane mode.
            */
            StepDetailFragment stepDetailFragment = new StepDetailFragment();

            //Sending the data to the fragment for setting into the containers.
            Bundle arguments = new Bundle();
            arguments.putParcelableArrayList(STEPS_KEY, stepsItemArrayList);
            arguments.putInt(CLICKED_POSITION, position);
            stepDetailFragment.setArguments(arguments);

            //Replacing the current fragment with the updated one.
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_detail_frame_layout, stepDetailFragment)
                    .commit();
        } else {
            /*
            Opening the detail activity for single pane mode.
            */
            Intent detailedIntent = new Intent(getApplicationContext(), StepDetailsActivity.class);
            detailedIntent.putExtra(CLICKED_POSITION, position);
            detailedIntent.putParcelableArrayListExtra(STEPS_KEY, stepsItemArrayList);
            startActivity(detailedIntent);
        }
    }
}

/*
This activity hosts the Steps List Fragment which is defined in the XML layout.
Also supports two-pane layout for larger screens like tablets.
*/
