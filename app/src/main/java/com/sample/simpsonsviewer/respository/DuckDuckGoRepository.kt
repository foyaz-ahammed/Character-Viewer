package com.sample.simpsonsviewer.respository

import com.sample.simpsonsviewer.api.DuckDuckGoService
import com.sample.simpsonsviewer.models.Character
import com.sample.simpsonsviewer.models.ResponseModel
import com.sample.simpsonsviewer.respository.mapper.ResponseMapper
import com.sample.simpsonsviewer.utility.Constants

/**
 * Repository class handling business logic fetching data
 */
class DuckDuckGoRepository(
    private val service: DuckDuckGoService,
    private val mapper: ResponseMapper
) {

    // Define a function to make the API call
    suspend fun getCharacters(): ResponseModel<List<Character>> {
        return try {
            val result = service.getCharacters(Constants.DEFAULT_QUERY, Constants.DEFAULT_FORMAT)
            ResponseModel.Success(mapper.mapResponseToCharacterList(result))
        } catch (e: Exception) {
            ResponseModel.Failure(e)
        }
    }
}