package me.akshanshjain.garnish.Fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.akshanshjain.garnish.Adapters.IngredientsRecyclerAdapter;
import me.akshanshjain.garnish.Objects.IngredientItem;
import me.akshanshjain.garnish.Objects.RecipeItem;
import me.akshanshjain.garnish.R;

public class IngredientsFragment extends Fragment {

    private Bundle bundle;
    private RecipeItem recipeItem;

    private RecyclerView ingredientsRecycler;
    private List<IngredientItem> ingredientItemList;
    private IngredientsRecyclerAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            recipeItem = bundle.getParcelable("RECIPEINFO");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ingredient_fragment_layout, container, false);

        ingredientsRecycler = view.findViewById(R.id.recycler_view_ingredients);
        ingredientItemList = new ArrayList<>();
        adapter = new IngredientsRecyclerAdapter(getContext().getApplicationContext(), ingredientItemList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        ingredientsRecycler.setLayoutManager(layoutManager);
        ingredientsRecycler.setItemAnimator(new DefaultItemAnimator());
        ingredientsRecycler.setAdapter(adapter);

        populateData();

        return view;
    }

    private void populateData() {
        Log.d("ADebug", recipeItem.getId() + "");
        Log.d("ADebug", recipeItem.getName() + "");
        Log.d("ADebug", recipeItem.getCookingTime() + "");
        Log.d("ADebug", recipeItem.getImageUrl() + "");
        Log.d("ADebug", recipeItem.getIngredientItemList() + "");
        Log.d("ADebug", recipeItem.getStepsItemList() + "");
        Log.d("ADebug", recipeItem.getServings() + "");
    }
}
