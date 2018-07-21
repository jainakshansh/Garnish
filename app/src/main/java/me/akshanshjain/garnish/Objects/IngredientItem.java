package me.akshanshjain.garnish.Objects;

public class IngredientItem {

    private int quantity;
    private String measure;
    private String ingredient;

    private IngredientItem() {
    }

    public IngredientItem(int quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }
}
