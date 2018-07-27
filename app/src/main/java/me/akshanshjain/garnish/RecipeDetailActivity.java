package me.akshanshjain.garnish;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import me.akshanshjain.garnish.Objects.RecipeItem;

public class RecipeDetailActivity extends AppCompatActivity {

    private RecipeItem recipeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Bundle data = getIntent().getExtras();
        if (data != null) {
            recipeItem = data.getParcelable("RECIPEINFO");
        }
    }
}
