package com.developer.kimy.goodnessbakes;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.developer.kimy.goodnessbakes.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.anything;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class NextRecipeButtonTest {

    @Rule public ActivityTestRule<MainActivity> recipeDetailFragmentActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    private IdlingResource mIdlingResource;
    private String mRecipeName = "Nutella Pie";

    @Before
    public void registerIdlingResource() {
        mIdlingResource = recipeDetailFragmentActivityTestRule.getActivity().getIdlingResource();

        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void checkForDataInAdapter() {

        //find the adapter view and perform a click action
        onView(withId(R.id.main_recipe_list_rv)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.change_btn)).perform(click());

        //check if the data is same as expected
        onView(withId(R.id.recipe_heading_tv)).check(matches(withText(mRecipeName)));

    }

    @After
    public void unregisterIdlingResource() {
        if(mIdlingResource != null){
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

}
