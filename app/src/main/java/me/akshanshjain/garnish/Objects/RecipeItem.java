package me.akshanshjain.garnish.Objects;

import java.util.List;

public class RecipeItem {

    private int id;
    private String name;
    private List<IngredientItem> ingredientItemList;
    private List<StepsItem> stepsItemList;
    private int servings;
    private String imageUrl;

    private RecipeItem() {
    }

    public RecipeItem(int id, String name, List<IngredientItem> ingredientItemList, List<StepsItem> stepsItemList, int servings, String imageUrl) {
        this.id = id;
        this.name = name;
        this.ingredientItemList = ingredientItemList;
        this.stepsItemList = stepsItemList;
        this.servings = servings;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<IngredientItem> getIngredientItemList() {
        return ingredientItemList;
    }

    public List<StepsItem> getStepsItemList() {
        return stepsItemList;
    }

    public int getServings() {
        return servings;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
