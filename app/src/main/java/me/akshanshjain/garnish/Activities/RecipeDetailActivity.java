package me.akshanshjain.garnish.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import me.akshanshjain.garnish.Adapters.IngredientsRecyclerAdapter;
import me.akshanshjain.garnish.Objects.IngredientItem;
import me.akshanshjain.garnish.Objects.RecipeItem;
import me.akshanshjain.garnish.Objects.StepsItem;
import me.akshanshjain.garnish.R;

public class RecipeDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView recipeImage;

    private Typeface QLight;

    private RecipeItem recipeItem;
    private static final String BUNDLE_KEY = "RECIPEINFO";
    private static final String INGREDIENTS_KEY = "INGREDIENTSINFO";
    private static final String STEPS_KEY = "STEPSINFO";
    private static final String RECIPE_NAME = "RECIPENAME";
    private static final String PREF_KEY = "RECIPEFAV";

    private TextView ingredientsLabel;
    private RecyclerView ingredientsRecycler;
    private Button showStepsButton;
    private FloatingActionButton fab;

    private ArrayList<IngredientItem> ingredientItemList;
    private IngredientsRecyclerAdapter ingredientsAdapter;

    private ArrayList<StepsItem> stepsItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        //Typeface for the current activity.
        QLight = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Light.ttf");

        //Setting up the toolbar for the activity.
        toolbar = findViewById(R.id.toolbar_recipe_detail);
        setSupportActionBar(toolbar);

        //Getting the data bundle from the passed intent.
        Bundle data = getIntent().getExtras();
        if (data != null) {
            recipeItem = data.getParcelable(BUNDLE_KEY);
            ingredientItemList = data.getParcelableArrayList(INGREDIENTS_KEY);
            stepsItemList = data.getParcelableArrayList(STEPS_KEY);
        }

        //Setting the up button for the toolbar.
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Setting collapsing toolbar layout title.
        collapsingToolbarLayout = findViewById(R.id.ctl_recipe_detail);
        if (getIntent().getExtras() != null) {
            collapsingToolbarLayout.setTitle(recipeItem.getName());
        }

        //Customizing the Collapsing Toolbar appearance.
        collapsingToolbarLayout.setCollapsedTitleTypeface(QLight);
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.chocolateBrown));
        collapsingToolbarLayout.setExpandedTitleTypeface(QLight);
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.chocolateBrown));

        //Setting the image for the collapsing toolbar layout.
        recipeImage = findViewById(R.id.recipe_image_detail);
        Picasso.get()
                .load(recipeItem.getImageUrl())
                .into(recipeImage);

        //Setting the typefaces for the local views.
        ingredientsLabel = findViewById(R.id.ingredients_label_detail);
        ingredientsLabel.setTypeface(QLight);

        showStepsButton = findViewById(R.id.show_steps_button);
        showStepsButton.setTypeface(QLight);

        showStepsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailedStepsIntent = new Intent(getApplicationContext(), StepListActivity.class);
                detailedStepsIntent.putExtra(RECIPE_NAME, recipeItem.getName());
                detailedStepsIntent.putParcelableArrayListExtra(STEPS_KEY, stepsItemList);

                //Starting the activity.
                startActivity(detailedStepsIntent);
            }
        });

        /*
        Preparing the recycler view and setting up the ingredients list.
        */
        settingIngredients();

        /*
        Saving the ingredients in the shared preference as strings.
        This information will be used to update the widgets.
        */
        fab = findViewById(R.id.fab_recipe_favorite);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFavorite();
            }
        });
    }

    /*
    Setting the ingredients into a recycler view in a card format.
    */
    private void settingIngredients() {
        ingredientsRecycler = findViewById(R.id.recycler_view_ingredients);
        ingredientsAdapter = new IngredientsRecyclerAdapter(this, ingredientItemList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ingredientsRecycler.setLayoutManager(layoutManager);
        ingredientsRecycler.setItemAnimator(new DefaultItemAnimator());
        ingredientsRecycler.setHasFixedSize(true);
        ingredientsRecycler.setNestedScrollingEnabled(false);
        ingredientsRecycler.setAdapter(ingredientsAdapter);

        //Updating the views after data binding.
        ingredientsAdapter.notifyDataSetChanged();
    }

    /*
    This function creates a shared preference and saves or deletes the recipe information.
    This information will be used to display recipe ingredients in the home screen widget.
    */
    private void addToFavorite() {
        StringBuilder ingredientsAll = new StringBuilder();
        for (int i = 0; i < ingredientItemList.size(); i++) {
            String quantity = String.valueOf(ingredientItemList.get(i).getQuantity());
            String measure = ingredientItemList.get(i).getMeasure();
            String ingredient = ingredientItemList.get(i).getIngredient();

            ingredientsAll.append(quantity).append(" ").append(measure).append(" ").append(ingredient).append("\n");
        }

        SharedPreferences sharedPreferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        boolean favorite = sharedPreferences.getBoolean("ISFAV", false);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NAME", recipeItem.getName());
        editor.putString("INGREDS", ingredientsAll.toString());
        if (!favorite) {
            editor.putBoolean("ISFAV", true);
        } else {
            editor.putBoolean("ISFAV", false);
        }
        editor.apply();

        Toast.makeText(this, "Recipe added to Widget!", Toast.LENGTH_SHORT).show();
    }
}
