package com.sample.simpsonsviewer.repository.mapper

import com.sample.simpsonsviewer.models.DuckDuckGoResponse
import com.sample.simpsonsviewer.models.Icon
import com.sample.simpsonsviewer.models.RelatedTopic
import com.sample.simpsonsviewer.respository.mapper.ResponseMapper
import com.sample.simpsonsviewer.utility.Constants
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class ResponseMapperTest {

    @Mock
    lateinit var response: DuckDuckGoResponse

    @Test
    fun mapResponseToCharacterList_returnsCorrectList() {
        // Arrange
        val mapper = ResponseMapper()
        val relatedTopics = listOf(
            RelatedTopic(
                text = "Apu - Apu is a recurring character.",
                result = "<a href=\"https://www.google.com/search?q=Apu\">Apu</a> - A recurring character",
                firstURL = "https://duckduckgo.com/Apu_Nahasapeemapetilan",
                icon = Icon(uRL = "/kotlin.png", width = "", height = "")
            )
        )
        whenever(response.relatedTopics).thenReturn(relatedTopics)

        // Act
        val characterList = mapper.mapResponseToCharacterList(response)

        // Assert
        assertEquals(characterList.size, 1)
        assertEquals(characterList[0].title, "Apu")
        assertEquals(characterList[0].description, "Apu is a recurring character.")
        assertEquals(characterList[0].htmlDescription, relatedTopics[0].result)
        assertEquals(characterList[0].url, relatedTopics[0].firstURL)
        assertEquals(characterList[0].icon, Constants.BASE_URL + relatedTopics[0].icon.uRL)
    }
}