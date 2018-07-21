package me.akshanshjain.garnish.Objects;

public class RecipeItem {

    private int id;
    private String name;
    private IngredientItem ingredients;
    private StepsItem recipeSteps;
    private int servings;
    private String imageUrl;

    private RecipeItem() {
    }

    public RecipeItem(int id, String name, IngredientItem ingredients, StepsItem recipeSteps, int servings, String imageUrl) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.recipeSteps = recipeSteps;
        this.servings = servings;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public IngredientItem getIngredients() {
        return ingredients;
    }

    public StepsItem getRecipeSteps() {
        return recipeSteps;
    }

    public int getServings() {
        return servings;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
