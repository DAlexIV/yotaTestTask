package com.hse.dalexiv.testtask;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.hse.dalexiv.testtask.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.hse.dalexiv.testtask.OrientationChangeAction.orientationLandscape;
import static com.hse.dalexiv.testtask.OrientationChangeAction.orientationPortrait;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class URLRotationTest extends BaseUrlTest {
    String url = "https://www.ihgagent.com/documents/19042802/19042864/Effective+Java.pdf";

    @Test
    public void enterUrl() {
        onView(withId(R.id.editTextUrl)).perform(typeText(url));
        onView(isRoot()).perform(orientationLandscape());
        onView(withId(R.id.buttonGo)).perform(click());
        onView(isRoot()).perform(orientationPortrait());

        onView(withId(R.id.textView)).check(matches(not(withText(""))));
    }
}