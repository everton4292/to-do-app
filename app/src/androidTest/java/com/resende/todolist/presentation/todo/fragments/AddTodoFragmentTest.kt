package com.resende.todolist.presentation.todo.fragments

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.resende.todolist.R
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class AddTodoFragmentTest {

    @Test
    fun teste_isAddTodoFragmentVisible() {
        val fragmentArgs = bundleOf("" to 0)
        val scenario = launchFragmentInContainer<AddTodoFragment>(fragmentArgs, themeResId = R.style.AppTheme).moveToState(
            Lifecycle.State.RESUMED)

        onView(withId(R.id.todo_form_save_button)).check(matches(isDisplayed()))
    }


}