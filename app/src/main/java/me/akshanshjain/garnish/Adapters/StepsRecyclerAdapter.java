package me.akshanshjain.garnish.Adapters;

import android.content.Context;
import android.graphics.Typeface;
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
    private Typeface QLight;
    private StepItemClickListener itemClickListener;

    /*
    Constructor with the click listener as a parameter.
    */
    public StepsRecyclerAdapter(Context context, ArrayList<StepsItem> stepsItemList, StepItemClickListener itemClickListener) {
        this.context = context;
        this.stepsItemList = stepsItemList;
        this.itemClickListener = itemClickListener;
        QLight = Typeface.createFromAsset(context.getAssets(), "fonts/Quicksand-Light.ttf");
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView stepNumberTv, stepShortDescTv;

        public StepsViewHolder(View view) {
            super(view);
            stepNumberTv = view.findViewById(R.id.step_number);
            stepShortDescTv = view.findViewById(R.id.step_short_desc);

            itemView.setOnClickListener(this);
        }

        /*
        Getting the item clicked and passing it to the listener.
        The position is retrieved using an inbuilt function.
        */
        @Override
        public void onClick(View view) {
            int clickedPos = getAdapterPosition();
            itemClickListener.onStepItemClickListener(clickedPos);
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
        StepsItem stepsItem = stepsItemList.get(position);

        holder.stepNumberTv.setTypeface(QLight);
        holder.stepNumberTv.setText(String.valueOf(stepsItem.getId()));

        holder.stepShortDescTv.setTypeface(QLight);
        holder.stepShortDescTv.setText(stepsItem.getShortDesc());
    }

    @Override
    public int getItemCount() {
        return stepsItemList.size();
    }

    /*
    Creating an interface to send the clicked item position to the activity.
    */
    public interface StepItemClickListener {
        void onStepItemClickListener(int clickedItemIndex);
    }
}
