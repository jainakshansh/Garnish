package me.akshanshjain.garnish.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.akshanshjain.garnish.Objects.RecipeItem;
import me.akshanshjain.garnish.R;
import me.akshanshjain.garnish.Activities.RecipeDetailActivity;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context context;
    private List<RecipeItem> recipeItemList;
    private Typeface QLight;

    /*
    P
    */
    private static final String BUNDLE_KEY = "RECIPEINFO";
    private static final String INGREDIENTS_KEY = "INGREDIENTSINFO";
    private static final String STEPS_KEY = "STEPSINFO";

    public RecipeAdapter(Context context, List<RecipeItem> recipeItemList) {
        this.context = context;
        this.recipeItemList = recipeItemList;
        QLight = Typeface.createFromAsset(context.getAssets(), "fonts/Quicksand-Light.ttf");
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        private ImageView recipeImage;
        private TextView recipeName;
        private TextView recipeTime, recipeIngredientsTotal, recipeServingsTotal;
        private Button showRecipe;

        public RecipeViewHolder(View view) {
            super(view);
            //Referencing all the view from the XML to the inflated layout.
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
        View view;
        /*
        Checking for the landscape and portrait mode layouts.
        Changed the placements of child views with simple Linear Layout weight separation.
        Constant '1' is set for Portrait and '2' is set for Landscape.
        */
        if (context.getResources().getConfiguration().orientation == 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item_layout, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item_layout_land, parent, false);
        }

        /*
        Returning the inflated layout set into view to the view holder.
        */
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipeViewHolder holder, int position) {
        //Getting the recipe items details from the list at the current adapter position.
        final RecipeItem recipeItem = recipeItemList.get(holder.getAdapterPosition());

        //Loading the image of the recipe.
        Picasso.get()
                .load(recipeItem.getImageUrl())
                .into(holder.recipeImage);

        //Adding the name of the recipe.
        holder.recipeName.setTypeface(QLight);
        holder.recipeName.setText(recipeItem.getName());

        //Adding cooking time for the recipe.
        holder.recipeTime.setTypeface(QLight);
        holder.recipeTime.setText(recipeItem.getCookingTime());

        //Adding number of ingredients required for the recipe.
        int numberOfIng = recipeItem.getIngredientItemList().size();
        final String ingredients = String.valueOf(numberOfIng) + " ingredients";
        holder.recipeIngredientsTotal.setTypeface(QLight);
        holder.recipeIngredientsTotal.setText(ingredients);

        //Adding the total servings for the recipe.
        String servings = String.valueOf(recipeItem.getServings() + " servings");
        holder.recipeServingsTotal.setTypeface(QLight);
        holder.recipeServingsTotal.setText(servings);

        holder.showRecipe.setTypeface(QLight);
        holder.showRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailedRecipeIntent = new Intent(context.getApplicationContext(), RecipeDetailActivity.class);
                detailedRecipeIntent.putExtra(BUNDLE_KEY, recipeItem);
                detailedRecipeIntent.putParcelableArrayListExtra(INGREDIENTS_KEY, new ArrayList<Parcelable>(recipeItemList.get(holder.getAdapterPosition()).getIngredientItemList()));
                detailedRecipeIntent.putParcelableArrayListExtra(STEPS_KEY, new ArrayList<Parcelable>(recipeItemList.get(holder.getAdapterPosition()).getStepsItemList()));
                context.startActivity(detailedRecipeIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeItemList.size();
    }
}
