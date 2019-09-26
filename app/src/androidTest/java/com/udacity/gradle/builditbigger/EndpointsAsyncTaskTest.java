package com.udacity.gradle.builditbigger;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import com.udacity.gradle.builditbigger.endpointstask.EndpointsAsyncTask;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

public class EndpointsAsyncTaskTest {

    private static final String DEFAULT_JOKE =
            "Why don't blind people skydive?\nBecause it scares the crap out of their dogs.";

    private EndpointsAsyncTask endpointsAsyncTask;
    private CountDownLatch signal = new CountDownLatch(1);

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        endpointsAsyncTask = new EndpointsAsyncTask(activityRule.getActivity().getFragment()) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                assertThat(result).isEqualTo(DEFAULT_JOKE);
                signal.countDown();
            }
        };
    }

    @Test
    public void onPostExecute() throws InterruptedException {
        endpointsAsyncTask.execute();
        signal.await();
    }
}