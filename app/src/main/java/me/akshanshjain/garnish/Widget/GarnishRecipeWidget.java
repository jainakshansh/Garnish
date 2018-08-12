package me.akshanshjain.garnish.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.RemoteViews;

import me.akshanshjain.garnish.Activities.LandingActivity;
import me.akshanshjain.garnish.R;

/**
 * Implementation of App Widget functionality.
 */
public class GarnishRecipeWidget extends AppWidgetProvider {

    private static final String PREF_KEY = "RECIPEFAV";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.garnish_recipe_widget);

        Intent intent = new Intent(context, LandingActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        String recipeName = "", ingredientString = "";

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.garnish_recipe_widget);

        Intent showRecipeIntent = new Intent(context, LandingActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, showRecipeIntent, 0);
        views.setOnClickPendingIntent(R.id.show_recipe_widget, pendingIntent);

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("ISFAV", false)) {
            recipeName = sharedPreferences.getString("NAME", "");
            ingredientString = sharedPreferences.getString("INGREDS", "");
        }

        views.setTextViewText(R.id.recipe_name_widget, recipeName);
        views.setTextViewText(R.id.ingredients_widget, ingredientString);

        AppWidgetManager.getInstance(context).updateAppWidget(
                new ComponentName(context, GarnishRecipeWidget.class), views);
    }
}

