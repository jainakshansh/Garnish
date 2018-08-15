package me.akshanshjain.garnish;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Toolbar;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.akshanshjain.garnish.Activities.LandingActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class MainActivityIntentTest {

    public static final String RECIPE_NAME = "Brownies";

    @Rule
    public IntentsTestRule<LandingActivity> landingActivityIntentsTestRule =
            new IntentsTestRule<>(LandingActivity.class);

    @Before
    public void stubs() {

    }
}
