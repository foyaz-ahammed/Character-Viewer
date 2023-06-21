package com.sample.simpsonsviewer.models

data class DuckDuckGoUIModel(
    val allCharacterList: List<Character> = emptyList(),
    val characterList: List<Character> = emptyList(),
    val selectedCharacter: Character? = null
)