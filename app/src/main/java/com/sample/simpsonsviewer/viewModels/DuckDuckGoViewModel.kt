package com.sample.simpsonsviewer.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.simpsonsviewer.models.Character
import com.sample.simpsonsviewer.models.DuckDuckGoUIModel
import com.sample.simpsonsviewer.models.DuckDuckGoUIObservable
import com.sample.simpsonsviewer.models.ResponseModel.Success
import com.sample.simpsonsviewer.models.ResponseModel.Failure
import com.sample.simpsonsviewer.respository.DuckDuckGoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * View model class used on List and Detail fragments
 */
class DuckDuckGoViewModel(private val repository: DuckDuckGoRepository) : ViewModel() {

    private val _duckDuckGoData = MutableLiveData<DuckDuckGoUIObservable>()
    val duckDuckGoData: LiveData<DuckDuckGoUIObservable>
        get() = _duckDuckGoData

    private var duckDuckGoUIObservable = DuckDuckGoUIObservable()
    private var job: Job? = null
    private var currentKeyword = ""

    companion object {
        val Tag = DuckDuckGoUIModel::class.simpleName
    }

    fun getCharacters() {
        // Initially update data with loading
        duckDuckGoUIObservable = duckDuckGoUIObservable
            .asLoadingListItem()
        _duckDuckGoData.postValue(duckDuckGoUIObservable)

        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getCharacters()) {
                is Success -> {
                    Log.d(Tag, "Success - ${result.value}")

                    val filteredResult = if (currentKeyword.isEmpty()) {
                        result.value
                    } else {
                        result.value.filter {
                            it.title.contains(currentKeyword, true) || it.description.contains(currentKeyword, true)
                        }
                    }
                    val model: DuckDuckGoUIModel = duckDuckGoUIObservable.result?:DuckDuckGoUIModel()
                    duckDuckGoUIObservable = duckDuckGoUIObservable
                        .asDoneLoadingListItem()
                        .withResult(
                            model.copy(
                                allCharacterList = result.value,
                                characterList = filteredResult
                            )
                        )
                        .withError(null)
                    _duckDuckGoData.postValue(duckDuckGoUIObservable)
                }
                is Failure -> {
                    Log.i(Tag, "Failure - ${result.exception}")

                    duckDuckGoUIObservable = duckDuckGoUIObservable
                        .asDoneLoadingListItem()
                        .withError(result.exception)
                    _duckDuckGoData.postValue(duckDuckGoUIObservable)
                }
            }
        }
    }

    fun searchCharacters(keyword: String) {
        job?.cancel()
        job = viewModelScope.launch {
            currentKeyword = keyword
            duckDuckGoUIObservable.result?.let { model ->
                val filteredResult = if (keyword.isEmpty()) {
                    model.allCharacterList
                } else {
                    model.allCharacterList.filter {
                        it.title.contains(keyword, true) || it.description.contains(keyword, true)
                    }
                }

                duckDuckGoUIObservable = duckDuckGoUIObservable
                    .asDoneLoadingListItem()
                    .withResult(
                        model.copy(
                            characterList = filteredResult
                        )
                    )
                    .withError(null)
                _duckDuckGoData.postValue(duckDuckGoUIObservable)
            }
        }
    }

    fun setSelectedCharacter(character: Character) {
        duckDuckGoUIObservable.result?.let { model ->
            duckDuckGoUIObservable = duckDuckGoUIObservable
                .asDoneLoadingDetailItem()
                .withResult(
                    model.copy(
                        selectedCharacter = character
                    )
                )
                .withError(null)
            _duckDuckGoData.postValue(duckDuckGoUIObservable)
        }
    }
}