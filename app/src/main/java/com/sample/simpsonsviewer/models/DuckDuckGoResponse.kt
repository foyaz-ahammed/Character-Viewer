package com.sample.simpsonsviewer.models

import com.google.gson.annotations.SerializedName

/**
 * Service response class
 */
data class DuckDuckGoResponse(
    @SerializedName("Abstract")
    val abstract: String,
    @SerializedName("AbstractSource")
    val abstractSource: String,
    @SerializedName("AbstractText")
    val abstractText: String,
    @SerializedName("AbstractURL")
    val abstractURL: String,
    @SerializedName("Answer")
    val answer: String,
    @SerializedName("AnswerType")
    val answerType: String,
    @SerializedName("Definition")
    val definition: String,
    @SerializedName("DefinitionSource")
    val definitionSource: String,
    @SerializedName("DefinitionURL")
    val definitionURL: String,
    @SerializedName("Entity")
    val entity: String,
    @SerializedName("Heading")
    val heading: String,
    @SerializedName("Image")
    val image: String,
    @SerializedName("ImageHeight")
    val imageHeight: Int,
    @SerializedName("ImageIsLogo")
    val imageIsLogo: Int,
    @SerializedName("ImageWidth")
    val imageWidth: Int,
    @SerializedName("Infobox")
    val infobox: String,
    @SerializedName("Redirect")
    val redirect: String,
    @SerializedName("RelatedTopics")
    val relatedTopics: List<RelatedTopic>,
    @SerializedName("Results")
    val results: List<Any>,
    @SerializedName("Type")
    val type: String
)

data class RelatedTopic(
    @SerializedName("FirstURL")
    val firstURL: String,
    @SerializedName("Icon")
    val icon: Icon,
    @SerializedName("Result")
    val result: String,
    @SerializedName("Text")
    val text: String
)

data class Icon(
    @SerializedName("Height")
    val height: String,
    @SerializedName("URL")
    val uRL: String,
    @SerializedName("Width")
    val width: String
)
