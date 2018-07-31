package me.akshanshjain.garnish.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import me.akshanshjain.garnish.Objects.StepsItem;

public class StepsRecyclerAdapter extends RecyclerView.Adapter<> {

    private Context context;
    private ArrayList<StepsItem> stepsItemList;

    public StepsRecyclerAdapter(Context context, ArrayList<StepsItem> stepsItemList) {
        this.context = context;
        this.stepsItemList = stepsItemList;
    }
}
