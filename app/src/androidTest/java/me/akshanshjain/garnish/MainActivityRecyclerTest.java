package me.akshanshjain.garnish;

import android.os.Handler;
import android.os.Looper;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.akshanshjain.garnish.Activities.LandingActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class MainActivityRecyclerTest {

    public static final String INGREDIENTS = "Ingredients";

    @Rule
    public ActivityTestRule<LandingActivity> landingActivityTestRule =
            new ActivityTestRule<>(LandingActivity.class);

    @Test
    public void recyclerViewItemClick_startsRecipeDetail() {

        /*
        Needed to add Looper.prepare();
        Without that, the Handler was not capable of executing and was throwing an error.
        */
        Looper.prepare();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /*
                onData loads the adapter item before running tests on it.
                */
                onData(withId(R.id.show_recipe)).inAdapterView(withId(R.id.recycler_view_landing)).atPosition(0).perform(click());
                /*
                Finally checking if the new activity has started.
                This is done by checking the presence of ingredients label text.
                */
                onView(withId(R.id.ingredients_label_detail)).check(matches(withText(INGREDIENTS)));
            }
            /*
            Added a delay of 500 ms for the data to load.
            */
        }, 500);
    }
}
