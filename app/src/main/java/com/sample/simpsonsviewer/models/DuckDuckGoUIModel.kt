package com.sample.simpsonsviewer.models

/**
 * Data model class on view model
 */
data class DuckDuckGoUIModel(
    val allCharacterList: List<Character> = emptyList(),
    val characterList: List<Character> = emptyList(),
    val selectedCharacter: Character? = null
)