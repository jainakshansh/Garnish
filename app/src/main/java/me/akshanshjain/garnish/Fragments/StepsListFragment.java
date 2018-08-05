package me.akshanshjain.garnish.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.akshanshjain.garnish.Adapters.StepsRecyclerAdapter;
import me.akshanshjain.garnish.Objects.StepsItem;
import me.akshanshjain.garnish.R;

public class StepsListFragment extends Fragment {

    private RecyclerView stepsRecycler;
    private ArrayList<StepsItem> stepsItemList;
    private StepsRecyclerAdapter adapter;

    //Mandatory constructor for instantiating a fragment.
    public StepsListFragment() {
    }

    /*
    Inflating the layout and performing all the fragment related operations.
    */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflating the layout into the view.
        View rootView = inflater.inflate(R.layout.fragment_steps_list, container, false);

        //Setting up the recycler view.
        stepsRecycler = rootView.findViewById(R.id.steps_list_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        stepsRecycler.setLayoutManager(layoutManager);
        stepsRecycler.setItemAnimator(new DefaultItemAnimator());
        stepsRecycler.setHasFixedSize(true);

        //Setting the adapter to the recycler view.
        stepsItemList = new ArrayList<>();
        adapter = new StepsRecyclerAdapter(getContext(), stepsItemList);
        stepsRecycler.setAdapter(adapter);

        //Returning the root view.
        return rootView;
    }
}
