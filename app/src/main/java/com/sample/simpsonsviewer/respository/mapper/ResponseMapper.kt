package com.sample.simpsonsviewer.respository.mapper

import com.sample.simpsonsviewer.models.DuckDuckGoResponse
import com.sample.simpsonsviewer.models.Character
import com.sample.simpsonsviewer.utility.Constants

class ResponseMapper {
    fun mapResponseToCharacterList(response: DuckDuckGoResponse): List<Character> {
        return response.relatedTopics.map {
            val dashIndex = it.text.indexOf(" - ")
            val title: String
            val description: String
            if (dashIndex >= 0) {
                title = it.text.substring(0, dashIndex)
                description = it.text.substring(dashIndex + 3)
            } else {
                title = it.text
                description = it.text
            }
            val icon = Constants.BASE_URL + it.icon.uRL

            Character(
                title = title,
                description = description,
                htmlDescription = it.result,
                url = it.firstURL,
                icon = icon
            )
        }
    }
}