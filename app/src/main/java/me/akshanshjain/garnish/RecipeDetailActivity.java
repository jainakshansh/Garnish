package me.akshanshjain.garnish;

import android.graphics.Typeface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import me.akshanshjain.garnish.Adapters.ViewPagerAdapter;
import me.akshanshjain.garnish.Fragments.IngredientsFragment;
import me.akshanshjain.garnish.Fragments.StepsFragment;
import me.akshanshjain.garnish.Objects.RecipeItem;

public class RecipeDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView recipeImage;

    private Typeface QLight;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private RecipeItem recipeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        QLight = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Light.ttf");

        //Setting up the toolbar for the activity.
        toolbar = findViewById(R.id.toolbar_recipe_detail);
        setSupportActionBar(toolbar);

        //Getting the data bundle from the passed intent.
        Bundle data = getIntent().getExtras();
        if (data != null) {
            recipeItem = data.getParcelable("RECIPEINFO");
        }

        //Setting the up button for the toolbar.
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Setting collapsing toolbar layout title.
        collapsingToolbarLayout = findViewById(R.id.ctl_recipe_detail);
        collapsingToolbarLayout.setTitle(recipeItem.getName());

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

        /*
        Setting up the Tab Layout and View Pager.
        These are containers for Ingredients and Steps Fragments.
        Also linked both the views.
        */
        tabLayout = findViewById(R.id.tab_layout_recipe_detail);
        viewPager = findViewById(R.id.view_pager_recipe_detail);
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
    }

    /*
    Adding fragments and title to the adapter and syncing with the view pager.
    */
    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new IngredientsFragment(), "Ingredients");
        adapter.addFragment(new StepsFragment(), "Steps");
        viewPager.setAdapter(adapter);
    }
}
