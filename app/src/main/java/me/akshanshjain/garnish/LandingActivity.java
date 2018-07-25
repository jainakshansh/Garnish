package me.akshanshjain.garnish;

import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import me.akshanshjain.garnish.Adapters.RecipeAdapter;
import me.akshanshjain.garnish.Objects.IngredientItem;
import me.akshanshjain.garnish.Objects.RecipeItem;
import me.akshanshjain.garnish.Objects.StepsItem;

public class LandingActivity extends AppCompatActivity {

    private TextView titleText;
    private Typeface qMed, qLight;
    private Button viewRecipeButton;

    private RecyclerView recipeRecycler;
    private RecipeAdapter recipeAdapter;

    private List<RecipeItem> recipeItemList;
    private List<IngredientItem> ingredientItemList;
    private List<StepsItem> stepsItemList;

    private RequestQueue requestQueue;
    private JsonArrayRequest jsonArrayRequest;
    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    //String constants for the Recipe JSON parsing.
    private static final String RECIPE_ID = "id";
    private static final String RECIPE_NAME = "name";
    private static final String RECIPE_INGREDIENTS = "ingredients";
    private static final String RECIPE_STEPS = "steps";
    private static final String RECIPE_SERVINGS = "servings";
    private static final String RECIPE_IMAGE = "image";

    //String constants for the Ingredients JSON parsing.
    private static final String INGREDIENTS_QUANTITY = "quantity";
    private static final String INGREDIENTS_MEASURE = "measure";
    private static final String INGREDIENTS_INGREDIENT = "ingredient";

    //String constants for the Steps JSON parsing.
    private static final String STEPS_ID = "id";
    private static final String STEPS_SHORT_DESC = "shortDescription";
    private static final String STEPS_DESC = "description";
    private static final String STEPS_VIDEO_URL = "videoURL";
    private static final String STEPS_THUMBNAIL_URL = "thumbnailURL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        initViews();

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (connectivityManager.getActiveNetworkInfo() != null) {
            networkCalls();
        }
    }

    /*
    Initializing and Referencing views from XML.
     */
    private void initViews() {

        qLight = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Light.ttf");
        qMed = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Medium.ttf");
        titleText = findViewById(R.id.app_title_landing);
        titleText.setTypeface(qLight);

        viewRecipeButton = findViewById(R.id.view_recipe_button);
        viewRecipeButton.setTypeface(qMed);

        recipeRecycler = findViewById(R.id.recycler_view_landing);
        recipeItemList = new ArrayList<>();
        recipeAdapter = new RecipeAdapter(this, recipeItemList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recipeRecycler.setLayoutManager(layoutManager);
        recipeRecycler.setItemAnimator(new DefaultItemAnimator());
        recipeRecycler.setHasFixedSize(true);
        recipeRecycler.setAdapter(recipeAdapter);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recipeRecycler);

        ingredientItemList = new ArrayList<>();
        stepsItemList = new ArrayList<>();
    }

    private void networkCalls() {
        requestQueue = Volley.newRequestQueue(this);
        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BASE_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        extractFromJSON(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LandingActivity.this, getResources().getString(R.string.check_internet_connection), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recipeAdapter.notifyDataSetChanged();
                Log.d("ADebug", "Ingredient: " + recipeItemList.size());
            }
        }, 2000);
    }

    /*
    Extracting all the data about the recipe including steps and ingredients.
    */
    private void extractFromJSON(JSONArray baseJSONResponse) {
        try {
            //Parsing through all the child elements in the node.
            for (int i = 0; i < baseJSONResponse.length(); i++) {
                //Getting the first object from the array.
                JSONObject recipeObject = baseJSONResponse.getJSONObject(i);

                //Extracting relevant information from the object.
                int recipeID = recipeObject.getInt(RECIPE_ID);
                String recipeName = recipeObject.getString(RECIPE_NAME);
                int servings = recipeObject.getInt(RECIPE_SERVINGS);
                String recipeImage = "";

                //Getting the ingredients array.
                JSONArray ingredientsArray = recipeObject.getJSONArray(RECIPE_INGREDIENTS);

                //Parsing through the ingredients array.
                for (int c = 0; c < ingredientsArray.length(); c++) {
                    //Getting the ingredients object.
                    JSONObject ingredientObject = ingredientsArray.getJSONObject(c);

                    //Extracting relevant information from the object.
                    int quantity = ingredientObject.getInt(INGREDIENTS_QUANTITY);
                    String measure = ingredientObject.getString(INGREDIENTS_MEASURE);
                    String ingredient = ingredientObject.getString(INGREDIENTS_INGREDIENT);

                    //Adding all the ingredient elements to an array list.
                    ingredientItemList.add(new IngredientItem(quantity, measure, ingredient));
                }


                //Getting the steps array.
                JSONArray stepsArray = recipeObject.getJSONArray(RECIPE_STEPS);

                //Parsing through the steps array.
                for (int c = 0; c < stepsArray.length(); c++) {
                    //Getting the steps object.
                    JSONObject stepObject = stepsArray.getJSONObject(c);

                    //Extracting relevant information from the object.
                    int id = stepObject.getInt(STEPS_ID);
                    String shortDesc = stepObject.getString(STEPS_SHORT_DESC);
                    String description = stepObject.getString(STEPS_DESC);
                    String videoURL = stepObject.getString(STEPS_VIDEO_URL);
                    String thumbnailURL = stepObject.getString(STEPS_THUMBNAIL_URL);

                    //Adding all the step elements to an array list.
                    stepsItemList.add(new StepsItem(id, shortDesc, description, videoURL, thumbnailURL));
                }

                recipeImage = additionalDetails(i);

                //Adding all the recipe elements to an array list.
                recipeItemList.add(new RecipeItem(recipeID, recipeName, ingredientItemList, stepsItemList, servings, recipeImage, null));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String additionalDetails(int position) {
        switch (position) {
            case 0:
                return "https://singapore.theexpat.com/wp-content/uploads/2015/09/Comestivel-Desserts.png";
            case 1:
                return "http://www.muffinscakes.com/image/cache/data/Brownie/Chocolate-Brownie-750x570.png";
            case 2:
                return "https://balfours.com.au/content/uploads/2016/12/cakes-and-pastries_french-coffee-bar-cake-900x0-c-default.png";
            case 3:
                return "https://www.nuyofrozenyogurt.com/wp-content/uploads/2015/09/cheesecake.jpg";
        }
        return null;
    }
}
