package com.sample.simpsonsviewer.models

/**
 * Observable class to update values on view model
 */
class DuckDuckGoUIObservable(
    val result: DuckDuckGoUIModel? = null,
    val error: Exception? = null,
    val state: ProcessState = ProcessState.IDLE,
    val process: Process = Process.LOAD_LIST_ITEM
) {
    private fun copy(
        result: DuckDuckGoUIModel? = null,
        error: Exception? = null,
        state: ProcessState,
        process: Process
    ): DuckDuckGoUIObservable {
        return DuckDuckGoUIObservable(result, error, state, process)
    }

    fun withResult(result: DuckDuckGoUIModel) = this.copy(result, error, state, process)
    fun withError(error: Exception?) = this.copy(result, error, state, process)

    fun asLoadingListItem() = this.copy(
        state = ProcessState.PROCESSING,
        process = Process.LOAD_LIST_ITEM
    )

    fun asDoneLoadingListItem() = this.copy(
        state = ProcessState.DONE,
        process = Process.LOAD_LIST_ITEM
    )

    fun asLoadingDetailItem() = this.copy(
        state = ProcessState.PROCESSING,
        process = Process.LOAD_DETAIL
    )

    fun asDoneLoadingDetailItem() = this.copy(
        state = ProcessState.DONE,
        process = Process.LOAD_DETAIL
    )
}

enum class ProcessState {
    IDLE,
    PROCESSING,
    DONE;

    fun isProcessing() = this == PROCESSING
    fun isDone() = this == DONE
}

enum class Process {
    LOAD_LIST_ITEM,
    LOAD_DETAIL;

    fun isOnListItem() = this == LOAD_LIST_ITEM
    fun isOnDetail() = this == LOAD_DETAIL
}