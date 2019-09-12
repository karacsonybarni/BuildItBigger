package com.udacity.gradle.builditbigger;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class EndpointsAsyncTaskTest {

    private static final String DEFAULT_JOKE =
            "Why don't blind people skydive?\nBecause it scares the crap out of their dogs.";

    private EndpointsAsyncTask endpointsAsyncTask;

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        endpointsAsyncTask = new EndpointsAsyncTask(activityRule.getActivity());
    }

    @Test
    public void onPostExecute() {
        endpointsAsyncTask.execute();
        onView(withText(DEFAULT_JOKE)).check(matches(isDisplayed()));
    }
}