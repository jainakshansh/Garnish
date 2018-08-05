package me.akshanshjain.garnish.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class RecipeItem implements Parcelable {

    private int id;
    private String name;
    private List<IngredientItem> ingredientItemList;
    private List<StepsItem> stepsItemList;
    private int servings;
    private String imageUrl;
    private String cookingTime;

    private RecipeItem() {
    }

    public RecipeItem(int id, String name, List<IngredientItem> ingredientItemList, List<StepsItem> stepsItemList, int servings, String imageUrl, String cookingTime) {
        this.id = id;
        this.name = name;
        this.ingredientItemList = new ArrayList<>();
        this.stepsItemList = new ArrayList<>();
        this.servings = servings;
        this.imageUrl = imageUrl;
        this.cookingTime = cookingTime;
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

    /*
    All the Parcelable functions.
    */
    protected RecipeItem(Parcel in) {
        id = in.readInt();
        name = in.readString();
        ingredientItemList = new ArrayList<>();
        in.readList(ingredientItemList, IngredientItem.class.getClassLoader());
        stepsItemList = new ArrayList<>();
        in.readList(stepsItemList, StepsItem.class.getClassLoader());
        servings = in.readInt();
        imageUrl = in.readString();
        cookingTime = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeList(ingredientItemList);
        dest.writeList(stepsItemList);
        dest.writeInt(servings);
        dest.writeString(imageUrl);
        dest.writeString(cookingTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RecipeItem> CREATOR = new Creator<RecipeItem>() {
        @Override
        public RecipeItem createFromParcel(Parcel in) {
            return new RecipeItem(in);
        }

        @Override
        public RecipeItem[] newArray(int size) {
            return new RecipeItem[size];
        }
    };
}
