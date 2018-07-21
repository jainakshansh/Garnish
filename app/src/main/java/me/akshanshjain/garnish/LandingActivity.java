package me.akshanshjain.garnish;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LandingActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView recipeRecycler;

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

        //Setting up the toolbar for the activity.
        toolbar = findViewById(R.id.toolbar_landing);
        setSupportActionBar(toolbar);

        recipeRecycler = findViewById(R.id.recycler_view_landing);
    }

    /*
    Extracting all the data about the recipe including steps and ingredients.
     */
    private void extractFromJSON(String response) {
        try {
            //Getting the top node as the array element.
            JSONArray baseJSONResponse = new JSONArray(response);

            //Parsing through all the child elements in the node.
            for (int i = 0; i < baseJSONResponse.length(); i++) {
                //Getting the first object from the array.
                JSONObject recipeObject = baseJSONResponse.getJSONObject(i);

                //Extracting relevant information from the object.
                String recipeID = recipeObject.getString(RECIPE_ID);
                String recipeName = recipeObject.getString(RECIPE_NAME);
                String recipeServings = recipeObject.getString(RECIPE_SERVINGS);
                String recipeImage = recipeObject.getString(RECIPE_IMAGE);


                //Getting the ingredients array.
                JSONArray ingredientsArray = recipeObject.getJSONArray(RECIPE_INGREDIENTS);

                //Parsing through the ingredients array.
                for (int c = 0; c < ingredientsArray.length(); c++) {
                    //Getting the ingredients object.
                    JSONObject ingredientObject = ingredientsArray.getJSONObject(c);

                    //Extracting relevant information from the object.
                    String quantity = ingredientObject.getString(INGREDIENTS_QUANTITY);
                    String measure = ingredientObject.getString(INGREDIENTS_MEASURE);
                    String ingredient = ingredientObject.getString(INGREDIENTS_INGREDIENT);
                }


                //Getting the steps array.
                JSONArray stepsArray = recipeObject.getJSONArray(RECIPE_STEPS);

                //Parsing through the steps array.
                for (int c = 0; c < stepsArray.length(); c++) {
                    //Getting the steps object.
                    JSONObject stepObject = stepsArray.getJSONObject(c);

                    //Extracting relevant information from the object.
                    String id = stepObject.getString(STEPS_ID);
                    String shortDesc = stepObject.getString(STEPS_SHORT_DESC);
                    String description = stepObject.getString(STEPS_DESC);
                    String videoURL = stepObject.getString(STEPS_VIDEO_URL);
                    String thumbnailURL = stepObject.getString(STEPS_THUMBNAIL_URL);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
