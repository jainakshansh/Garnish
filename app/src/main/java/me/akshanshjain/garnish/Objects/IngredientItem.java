package me.akshanshjain.garnish.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class IngredientItem implements Parcelable {

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

    protected IngredientItem(Parcel in) {
        quantity = in.readInt();
        measure = in.readString();
        ingredient = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(quantity);
        dest.writeString(measure);
        dest.writeString(ingredient);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<IngredientItem> CREATOR = new Creator<IngredientItem>() {
        @Override
        public IngredientItem createFromParcel(Parcel in) {
            return new IngredientItem(in);
        }

        @Override
        public IngredientItem[] newArray(int size) {
            return new IngredientItem[size];
        }
    };

}
