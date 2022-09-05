package com.abcdandroid.data.remote.model

import com.google.gson.annotations.SerializedName

data class BooksResponse(val items: List<DtoBook>)


data class DtoBook(
    @field:SerializedName("volumeInfo")
    val volumeInfo: VolumeInfo? = null,

    @field:SerializedName("id")
    val id: String? = null,
)


data class VolumeInfo(
    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("imageLinks")
    val imageLinks: ImageLinks? = null,
)


data class ImageLinks(

    @field:SerializedName("thumbnail")
    val thumbnail: String? = null,

    @field:SerializedName("smallThumbnail")
    val smallThumbnail: String? = null
)





