package com.spykins.localefood.view;

import android.content.Intent;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Root;
import android.support.test.rule.ActivityTestRule;
import android.view.WindowManager;

import com.spykins.localefood.R;
import com.spykins.localefood.repository.FetchAddressIntentService;

import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mNotesActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

//    @Rule
//    public IntentsTestRule<MainActivity> intentsTestRule =
//            new IntentsTestRule<>(MainActivity.class);
    @Test
    public void checkThatTheToastShowsWhenUserDoesnotInputValue() {
        onView(withId(R.id.submit_button)).perform(click());
        onView(withText(R.string.enter_a_message)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkThatTheToastDoesnotShowWhenUserInputValue() {
        String address = "21 Montgomercy Montreal Quebec";
        onView(withId(R.id.address_text)).perform(typeText(address));
        onView(withId(R.id.address_text)).check(matches(withText(address)));
        onView(withId(R.id.submit_button)).perform(click());
        onView(withId(R.id.address_text)).perform(clearText());
    }



    @Test
    public void checkThatTheToastErrorFromServiceShowUp() {
        String address = "21 Montgomercy Montreal Quebec";
//        intended(allOf(
//                hasExtras(hasEntry(equalTo(Constants.LOCATION_ADDRESS), equalTo(address))),
//                toPackage("com.spykins.localefood")));


        //intending(toPackage("com.spykins.localefood")).respondWith(result);

        Intent serviceIntent =
                new Intent(InstrumentationRegistry.getTargetContext(),
                        FetchAddressIntentService.class);


    }

    public static class ToastMatcher extends TypeSafeMatcher<Root> {

        @Override
        public boolean matchesSafely(Root root) {
            int type = root.getWindowLayoutParams().get().type;
            if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
                IBinder windowToken = root.getDecorView().getWindowToken();
                IBinder appToken = root.getDecorView().getApplicationWindowToken();
                if (windowToken == appToken) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public void describeTo(org.hamcrest.Description description) {
            description.appendText("is toast");
        }
    }
}