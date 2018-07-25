package me.akshanshjain.garnish.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.akshanshjain.garnish.Objects.RecipeItem;
import me.akshanshjain.garnish.R;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context context;
    private List<RecipeItem> recipeItemList;
    private Typeface qMed, greatVibes;

    public RecipeAdapter(Context context, List<RecipeItem> recipeItemList) {
        this.context = context;
        this.recipeItemList = recipeItemList;
        qMed = Typeface.createFromAsset(context.getAssets(), "fonts/Quicksand-Medium.ttf");
        greatVibes = Typeface.createFromAsset(context.getAssets(), "fonts/GreatVibes-Regular.ttf");
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        private ImageView recipeImage;
        private TextView recipeName;
        private TextView recipeTime, recipeIngredientsTotal, recipeServingsTotal;

        public RecipeViewHolder(View view) {
            super(view);
            recipeImage = view.findViewById(R.id.recipe_image);
            recipeName = view.findViewById(R.id.recipe_name);
            recipeTime = view.findViewById(R.id.recipe_time);
            recipeIngredientsTotal = view.findViewById(R.id.recipe_ingredients);
            recipeServingsTotal = view.findViewById(R.id.recipe_servings);
        }
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item_layout, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        RecipeItem recipeItem = recipeItemList.get(position);

        Picasso.get()
                .load(recipeItem.getImageUrl())
                .into(holder.recipeImage);

        holder.recipeName.setTypeface(greatVibes);
        holder.recipeName.setText(recipeItem.getName());

        holder.recipeTime.setTypeface(qMed);

        holder.recipeServingsTotal.setTypeface(qMed);
        //holder.recipeServingsTotal.setText(recipeItem.getServings());
    }

    @Override
    public int getItemCount() {
        return recipeItemList.size();
    }
}
