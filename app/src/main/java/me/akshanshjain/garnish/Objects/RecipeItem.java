package me.akshanshjain.garnish.Objects;

import java.util.List;

public class RecipeItem {

    private int id;
    private String name;
    private List<IngredientItem> ingredientItemList;
    private List<StepsItem> stepsItemList;
    private int servings;
    private String imageUrl;
    private String cookingTime;
    private String description;

    private RecipeItem() {
    }

    public RecipeItem(int id, String name, List<IngredientItem> ingredientItemList, List<StepsItem> stepsItemList, int servings, String imageUrl, String cookingTime, String description) {
        this.id = id;
        this.name = name;
        this.ingredientItemList = ingredientItemList;
        this.stepsItemList = stepsItemList;
        this.servings = servings;
        this.imageUrl = imageUrl;
        this.cookingTime = cookingTime;
        this.description = description;
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

    public String getCookingTime() {
        return cookingTime;
    }

    public String getDescription() {
        return description;
    }
}
