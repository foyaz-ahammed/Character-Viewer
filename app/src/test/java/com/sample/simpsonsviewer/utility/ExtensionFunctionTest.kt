package com.sample.simpsonsviewer.utility

import android.content.res.Resources
import com.sample.simpsonsviewer.R
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class ExtensionFunctionTest {

    @Mock
    lateinit var resources: Resources

    @Test
    fun getItemFoundDescription_returnsCorrectString() {
        // Arrange
        val zero = 0
        val one = 1
        val two = 2
        whenever(resources.getString(R.string.no_item_found)).thenReturn("No item found")
        whenever(resources.getString(R.string.item_found_format, one)).thenReturn("1 item(s) found")
        whenever(resources.getString(R.string.item_found_format, two)).thenReturn("2 item(s) found")

        // Act
        val zeroResult = zero.getItemFoundDescription(resources)
        val oneResult = one.getItemFoundDescription(resources)
        val twoResult = two.getItemFoundDescription(resources)

        // Assert
        assertEquals(zeroResult, "No item found")
        assertEquals(oneResult, "1 item(s) found")
        assertEquals(twoResult, "2 item(s) found")
    }
}
