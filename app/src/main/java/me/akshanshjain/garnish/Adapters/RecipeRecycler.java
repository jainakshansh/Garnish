package me.akshanshjain.garnish.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.akshanshjain.garnish.Objects.RecipeItem;

public class RecipeRecycler extends RecyclerView.Adapter<RecipeRecycler.RecipeViewHolder> {

    private Context context;
    private List<RecipeItem> recipeItemList;
    private Typeface qMed, qBold;

    public RecipeRecycler(Context context, List<RecipeItem> recipeItemList) {
        this.context = context;
        this.recipeItemList = recipeItemList;
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        public RecipeViewHolder(View view) {
            super(view);
        }
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return recipeItemList.size();
    }
}
