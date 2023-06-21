package com.sample.simpsonsviewer.models

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotSame
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DuckDuckGoUIObservableTest {

    @Mock
    lateinit var result: DuckDuckGoUIModel

    @Mock
    lateinit var error: Exception

    @Test
    fun withResult_returnsNewObservableWithResult() {
        // Arrange
        val uiObservable = DuckDuckGoUIObservable()

        // Act
        val newUiObservable = uiObservable.withResult(result)

        // Assert
        assertNotSame(uiObservable, newUiObservable)
        assertEquals(newUiObservable.result, result)
        assertEquals(newUiObservable.error, uiObservable.error)
        assertEquals(newUiObservable.state, uiObservable.state)
        assertEquals(newUiObservable.process, uiObservable.process)
    }

    @Test
    fun withError_returnsNewObservableWithError() {
        // Arrange
        val uiObservable = DuckDuckGoUIObservable()

        // Act
        val newUiObservable = uiObservable.withError(error)

        // Assert
        assertNotSame(uiObservable, newUiObservable)
        assertEquals(newUiObservable.result, uiObservable.result)
        assertEquals(newUiObservable.error, error)
        assertEquals(newUiObservable.state, uiObservable.state)
        assertEquals(newUiObservable.process, uiObservable.process)
    }

    @Test
    fun asLoadingListItem_returnsNewObservableWithLoadingListItemState() {
        // Arrange
        val uiObservable = DuckDuckGoUIObservable()

        // Act
        val newUiObservable = uiObservable.asLoadingListItem()

        // Assert
        assertNotSame(uiObservable, newUiObservable)
        assertEquals(newUiObservable.result, uiObservable.result)
        assertEquals(newUiObservable.error, uiObservable.error)
        assertEquals(newUiObservable.state, ProcessState.PROCESSING)
        assertEquals(newUiObservable.process, Process.LOAD_LIST_ITEM)
    }

    @Test
    fun asDoneLoadingListItem_returnsNewObservableWithDoneLoadingListItemState() {
        // Arrange
        val uiObservable = DuckDuckGoUIObservable()

        // Act
        val newUiObservable = uiObservable.asDoneLoadingListItem()

        // Assert
        assertNotSame(uiObservable, newUiObservable)
        assertEquals(newUiObservable.result, uiObservable.result)
        assertEquals(newUiObservable.error, uiObservable.error)
        assertEquals(newUiObservable.state, ProcessState.DONE)
        assertEquals(newUiObservable.process, Process.LOAD_LIST_ITEM)
    }

    @Test
    fun asLoadingDetailItem_returnsNewObservableWithLoadingDetailItemState() {
        // Arrange
        val uiObservable = DuckDuckGoUIObservable()

        // Act
        val newUiObservable = uiObservable.asLoadingDetailItem()

        // Assert
        assertNotSame(uiObservable, newUiObservable)
        assertEquals(newUiObservable.result, uiObservable.result)
        assertEquals(newUiObservable.error, uiObservable.error)
        assertEquals(newUiObservable.state, ProcessState.PROCESSING)
        assertEquals(newUiObservable.process, Process.LOAD_DETAIL)
    }

    @Test
    fun asDoneLoadingDetailItem_returnsNewObservableWithDoneLoadingDetailItemState() {
        // Arrange
        val uiObservable = DuckDuckGoUIObservable()

        // Act
        val newUIObservable = uiObservable.asDoneLoadingDetailItem()

        // Assert
        assertNotSame(uiObservable,newUIObservable)
        assertEquals(newUIObservable.result ,uiObservable.result)
        assertEquals(newUIObservable.error ,uiObservable.error)
        assertEquals(newUIObservable.state ,ProcessState.DONE)
        assertEquals(newUIObservable.process ,Process.LOAD_DETAIL)
    }
}