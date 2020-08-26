import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.binah.R
import com.binah.activities.MainActivity
import org.hamcrest.core.IsNot.not

@RunWith(AndroidJUnit4::class)
@LargeTest
class UiTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun radioButtonCheck() {

        onView(withId(R.id.radioButtonAnswered))
            .perform(click());

        onView(withId(R.id.radioButtonAnswered))
            .check(matches(isChecked()));

        onView(withId(R.id.radioButtonAll))
            .check(matches(not(isChecked())))

        onView(withId(R.id.radioButtonAll))
            .check(matches(not(isChecked())))
    }
}