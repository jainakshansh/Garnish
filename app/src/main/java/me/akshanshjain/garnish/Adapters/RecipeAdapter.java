package me.akshanshjain.garnish.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.akshanshjain.garnish.Objects.IngredientItem;
import me.akshanshjain.garnish.Objects.RecipeItem;
import me.akshanshjain.garnish.Objects.StepsItem;
import me.akshanshjain.garnish.R;
import me.akshanshjain.garnish.RecipeDetailActivity;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context context;
    private List<RecipeItem> recipeItemList;
    private Typeface qLight;


    private RecipeItemClickListener recipeItemClickListener;

    private List<IngredientItem> ingredientItemList;
    private List<StepsItem> stepsItemList;

    public RecipeAdapter(Context context, List<RecipeItem> recipeItemList, RecipeItemClickListener listener) {
        this.context = context;
        this.recipeItemList = recipeItemList;
        recipeItemClickListener = listener;
        qLight = Typeface.createFromAsset(context.getAssets(), "fonts/Quicksand-Light.ttf");
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

            showRecipe.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedItemPosition = getAdapterPosition();
            recipeItemClickListener.onRecipeItemClickListener(clickedItemPosition);
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
        final RecipeItem recipeItem = recipeItemList.get(position);

        //Loading the image of the recipe.
        Picasso.get()
                .load(recipeItem.getImageUrl())
                .into(holder.recipeImage);

        //Adding the name of the recipe.
        holder.recipeName.setTypeface(qLight);
        holder.recipeName.setText(recipeItem.getName());

        //Adding cooking time for the recipe.
        holder.recipeTime.setTypeface(qLight);
        holder.recipeTime.setText(recipeItem.getCookingTime());

        //Adding number of ingredients required for the recipe.
        int numberOfIng = recipeItem.getIngredientItemList().size();
        final String ingredients = String.valueOf(numberOfIng) + " ingredients";
        holder.recipeIngredientsTotal.setTypeface(qLight);
        holder.recipeIngredientsTotal.setText(ingredients);

        //Adding the total servings for the recipe.
        String servings = String.valueOf(recipeItem.getServings()) + " servings";
        holder.recipeServingsTotal.setTypeface(qLight);
        holder.recipeServingsTotal.setText(servings);

        /*
        //Show Recipe button actions to go to detailed view.
        holder.showRecipe.setTypeface(qLight);
        holder.showRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Getting all the required information from the recipe list.
                int id = recipeItem.getId();
                Log.d("ADebug", "" + id);
                String name = recipeItem.getName();

                ingredientItemList = new ArrayList<>();
                stepsItemList = new ArrayList<>();
                ingredientItemList.addAll(recipeItem.getIngredientItemList());
                stepsItemList.addAll(recipeItem.getStepsItemList());

                int servings = recipeItem.getServings();
                String image = recipeItem.getImageUrl();
                String cookingTime = recipeItem.getCookingTime();

                RecipeItem recipe = new RecipeItem(id, name, ingredientItemList, stepsItemList, servings, image, cookingTime);

                //Starting the detailed recipe activity and passing in the parcelable bundle.
                Intent detailedRecipe = new Intent(context.getApplicationContext(), RecipeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(BUNDLE_KEY, recipe);
                detailedRecipe.putExtras(bundle);

                context.startActivity(detailedRecipe);
            }
        });
        */
    }

    @Override
    public int getItemCount() {
        return recipeItemList.size();
    }

    public interface RecipeItemClickListener {
        void onRecipeItemClickListener(int clickedItemIndex);
    }
}
