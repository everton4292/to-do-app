package com.resende.todolist.presentation.greetings

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.resende.todolist.R
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class GreetingsActivityTest{

    @Test
    fun test_isActivity_inView() {
        val activityScenario = ActivityScenario.launch(GreetingsActivity::class.java)

        onView(withId(R.id.greetingsActivity))
            .check(matches(isDisplayed()))
    }
}