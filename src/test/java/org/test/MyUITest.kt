package org.test

import com.github.mvysny.kaributesting.v8.*
import com.vaadin.ui.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.expect

/**
 * Tests the UI. Uses the Browserless testing approach as provided by the [Karibu Testing](https://github.com/mvysny/karibu-testing) library.
 */
class MyUITest {
    @BeforeEach
    fun mockVaadin() {
        MockVaadin.setup({ MyUI() })
    }

    @AfterEach
    fun tearDownVaadin() {
        MockVaadin.tearDown()
    }

    @Test
    fun `simple UI test`() {
        // MockVaadin.setup() prepared the UI for us; we can now read components from it.
        val layout = UI.getCurrent().content as VerticalLayout

        // simulate a text entry as if entered by the user
        _get<TextField> { caption = "Type your name here:" }._value = "Baron Vladimir Harkonnen"

        // simulate a button click as if clicked by the user
        _get<Button> { caption = "Click Me" }._click()

        // check that the layout now has three components
        // this test doesn't make much sense but it demoes the possibility of accessing
        // the Vaadin components directly, reading their state and even manipulating them.
        expect(3) { layout.componentCount }

        // verify that there is a single Label and assert on its value
        expect("Thanks Baron Vladimir Harkonnen, it works!") { _get<Label>().value }
    }
}
