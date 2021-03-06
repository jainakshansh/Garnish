package me.akshanshjain.garnish.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import me.akshanshjain.garnish.Adapters.StepsRecyclerAdapter;
import me.akshanshjain.garnish.Objects.StepsItem;
import me.akshanshjain.garnish.R;

public class StepsListFragment extends Fragment implements StepsRecyclerAdapter.StepItemClickListener {

    private RecyclerView stepsRecycler;
    private ArrayList<StepsItem> stepsItemList;
    private StepsRecyclerAdapter adapter;

    OnStepClickListener listener;

    private static final String STEPS_KEY = "STEPSINFO";
    private static final String SCROLL_POS = "SCROLLPOS";
    private int scrollPosition;

    //Mandatory constructor for instantiating a fragment.
    public StepsListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    /*
        This makes sure that host activity has implemented the click interface.
        If not, this throws an exception.
        */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (OnStepClickListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    /*
    Inflating the layout and performing all the fragment related operations.
    */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflating the layout into the view.
        View rootView = inflater.inflate(R.layout.fragment_steps_list, container, false);

        stepsItemList = new ArrayList<>();

        /*
        Getting the data from the host activity.
        */
        if (getArguments() != null) {
            stepsItemList = getArguments().getParcelableArrayList(STEPS_KEY);
        }

        //Setting up the recycler view.
        stepsRecycler = rootView.findViewById(R.id.steps_list_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        stepsRecycler.setLayoutManager(layoutManager);
        stepsRecycler.setItemAnimator(new DefaultItemAnimator());

        //Setting the adapter to the recycler view.
        adapter = new StepsRecyclerAdapter(getContext(), stepsItemList, this);
        stepsRecycler.setAdapter(adapter);

        //Returning the root view.
        return rootView;
    }

    /*
    This function gets the clicked item from the adapter using the interface.
    */
    @Override
    public void onStepItemClickListener(int clickedItemIndex) {
        /*
        Setting the variable to send the data received from the Adapter to the host Activity using the interface.
        */
        listener.onStepItemClicked(clickedItemIndex);
    }

    /*
    This interface sends the clicked item value retrieved from the adapter to the host activity.
    */
    public interface OnStepClickListener {
        void onStepItemClicked(int position);
    }


    /*
    Saving the scroll position as the instance state.
    */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        scrollPosition = (((LinearLayoutManager) stepsRecycler.getLayoutManager()).findFirstVisibleItemPosition());
        outState.putInt(SCROLL_POS, scrollPosition);
    }

    /*
    Getting the scroll position from the saved instance state.
    */
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.containsKey(SCROLL_POS)) {
            scrollPosition = savedInstanceState.getInt(SCROLL_POS);
            if (scrollPosition < 0) scrollPosition = 0;
            stepsRecycler.scrollToPosition(scrollPosition);
        }
    }
}
