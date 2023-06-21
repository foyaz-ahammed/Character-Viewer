package com.sample.simpsonsviewer.api

import com.sample.simpsonsviewer.models.DuckDuckGoResponse
import com.sample.simpsonsviewer.models.Icon
import com.sample.simpsonsviewer.models.RelatedTopic
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.stub

@RunWith(MockitoJUnitRunner::class)
class DuckDuckGoServiceTest {

    @Mock
    lateinit var service: DuckDuckGoService

    @Test
    fun getCharacters_callsServiceWithCorrectParameters() {
        // Arrange
        val query = "kotlin"
        val format = "json"
        val response = DuckDuckGoResponse(
            "",
            abstractSource = "Wikipedia",
            abstractText = "",
            abstractURL = "https://en.wikipedia.org/wiki/The_Simpsons_characters",
            answer = "",
            answerType = "",
            definition = "",
            definitionSource = "",
            definitionURL = "",
            entity = "",
            heading = "The Simpsons characters  ",
            image = "",
            imageHeight = 0,
            imageIsLogo = 0,
            imageWidth = 0,
            infobox = "",
            redirect = "",
            relatedTopics = mutableListOf(
                RelatedTopic(
                    firstURL = "https://duckduckgo.com/Apu_Nahasapeemapetilan",
                    icon = Icon("", "", ""),
                    result = "Apu Nahasapeemapetilan",
                    text = "Apu Nahasapeemapetilan"
                )
            ),
            results = emptyList(),
            type = ""
        )
        service.stub {
            onBlocking { getCharacters(query, format) }.doReturn(response)
        }

        // Assert
        runBlocking {
            val result = service.getCharacters(query, format)
            assertEquals(result, response)
        }
    }
}
