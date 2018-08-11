package me.akshanshjain.garnish.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import me.akshanshjain.garnish.Objects.RecipeItem;

@Database(entities = {RecipeItem.class}, version = 1, exportSchema = false)
public abstract class RecipeDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "recipes";
    private static RecipeDatabase sInstance;

    public static RecipeDatabase getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                //Creating a new database instance.
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        RecipeDatabase.class, RecipeDatabase.DATABASE_NAME)
                        .build();
            }
        }

        //Getting the database instance.
        return sInstance;
    }

    public abstract RecipeDao recipeDao();
}
