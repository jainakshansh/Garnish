package me.akshanshjain.garnish.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import me.akshanshjain.garnish.Objects.RecipeItem;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM recipes")
    List<RecipeItem> loadAllRecipes();

    @Insert
    void insertRecipe(RecipeItem recipeItem);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateRecipe(RecipeItem recipeItem);

    @Delete
    void deleteRecipe(RecipeItem recipeItem);

    @Query("SELECT * FROM recipes WHERE id = :recipeID")
    RecipeItem loadRecipeById(int recipeID);
}
