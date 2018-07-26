package me.akshanshjain.garnish.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.akshanshjain.garnish.Objects.RecipeItem;
import me.akshanshjain.garnish.R;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context context;
    private List<RecipeItem> recipeItemList;
    private Typeface qLight;

    public RecipeAdapter(Context context, List<RecipeItem> recipeItemList) {
        this.context = context;
        this.recipeItemList = recipeItemList;
        qLight = Typeface.createFromAsset(context.getAssets(), "fonts/Quicksand-Light.ttf");
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        private ImageView recipeImage;
        private TextView recipeName;
        private TextView recipeTime, recipeIngredientsTotal, recipeServingsTotal;
        private Button showRecipe;

        public RecipeViewHolder(View view) {
            super(view);
            recipeImage = view.findViewById(R.id.recipe_image);
            recipeName = view.findViewById(R.id.recipe_name);
            recipeTime = view.findViewById(R.id.recipe_time);
            recipeIngredientsTotal = view.findViewById(R.id.recipe_ingredients);
            recipeServingsTotal = view.findViewById(R.id.recipe_servings);
            showRecipe = view.findViewById(R.id.show_recipe);
        }
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item_layout, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipeViewHolder holder, int position) {
        RecipeItem recipeItem = recipeItemList.get(position);

        Picasso.get()
                .load(recipeItem.getImageUrl())
                .into(holder.recipeImage);

        holder.recipeName.setTypeface(qLight);
        holder.recipeName.setText(recipeItem.getName());

        holder.recipeTime.setTypeface(qLight);
        holder.recipeTime.setText(recipeItem.getCookingTime());

        String ingredients = String.valueOf(recipeItem.getIngredientItemList().size()) + " ingredients";
        holder.recipeIngredientsTotal.setTypeface(qLight);
        holder.recipeIngredientsTotal.setText(ingredients);

        String servings = String.valueOf(recipeItem.getServings()) + " servings";
        holder.recipeServingsTotal.setTypeface(qLight);
        holder.recipeServingsTotal.setText(servings);

        holder.showRecipe.setTypeface(qLight);
        holder.showRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.showRecipe.setBackground(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.button_border_filled));
                holder.showRecipe.setTextColor(ContextCompat.getColor(context.getApplicationContext(), android.R.color.white));
                //TODO: Need to start the detailed activity on click of show recipe button.
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeItemList.size();
    }
}
