package me.akshanshjain.garnish.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.akshanshjain.garnish.Objects.StepsItem;
import me.akshanshjain.garnish.R;

public class StepsRecyclerAdapter extends RecyclerView.Adapter<StepsRecyclerAdapter.StepsViewHolder> {

    private Context context;
    private ArrayList<StepsItem> stepsItemList;

    public StepsRecyclerAdapter(Context context, ArrayList<StepsItem> stepsItemList) {
        this.context = context;
        this.stepsItemList = stepsItemList;
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder {

        private TextView stepNumberTv, stepShortDescTv;

        public StepsViewHolder(View view) {
            super(view);
        }
    }

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_list_item_layout, parent, false);
        return new StepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return stepsItemList.size();
    }
}
