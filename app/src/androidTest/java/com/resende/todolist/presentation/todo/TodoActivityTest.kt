package com.resende.todolist.presentation.todo

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.resende.todolist.R
import org.junit.Test

import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class TodoActivityTest {

    @Test
    fun test_isAddTodoButtonVisible() {
        val activityScenario = ActivityScenario.launch(TodoActivity::class.java)

         onView(withId(R.id.plusAccButton)).check(matches(isDisplayed()))
    }

    @Test
    fun test_NavigateToAddTodo() {
        val activityScenario = ActivityScenario.launch(TodoActivity::class.java)

        onView(withId(R.id.plusAccButton)).perform(click())

        onView(withId(R.id.addToDoFragment)).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.todoActivity)).check(matches(isDisplayed()))
    }
}