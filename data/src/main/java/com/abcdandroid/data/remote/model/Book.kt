package com.abcdandroid.data.remote.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(

	@Json(name="saleInfo")
	val saleInfo: SaleInfo? = null,

	@Json(name="searchInfo")
	val searchInfo: SearchInfo? = null,

	@Json(name="kind")
	val kind: String? = null,

	@Json(name="volumeInfo")
	val volumeInfo: VolumeInfo? = null,

	@Json(name="etag")
	val etag: String? = null,

	@Json(name="id")
	val id: String? = null,

	@Json(name="accessInfo")
	val accessInfo: AccessInfo? = null,

	@Json(name="selfLink")
	val selfLink: String? = null
) : Parcelable

@Parcelize
data class SearchInfo(

	@Json(name="textSnippet")
	val textSnippet: String? = null
) : Parcelable

@Parcelize
data class ImageLinks(

	@Json(name="thumbnail")
	val thumbnail: String? = null,

	@Json(name="smallThumbnail")
	val smallThumbnail: String? = null
) : Parcelable

@Parcelize
data class Pdf(

	@Json(name="isAvailable")
	val isAvailable: Boolean? = null
) : Parcelable

@Parcelize
data class ReadingModes(

	@Json(name="image")
	val image: Boolean? = null,

	@Json(name="text")
	val text: Boolean? = null
) : Parcelable

@Parcelize
data class SaleInfo(

	@Json(name="country")
	val country: String? = null,

	@Json(name="isEbook")
	val isEbook: Boolean? = null,

	@Json(name="saleability")
	val saleability: String? = null
) : Parcelable

@Parcelize
data class VolumeInfo(

	@Json(name="industryIdentifiers")
	val industryIdentifiers: List<IndustryIdentifiersItem?>? = null,

	@Json(name="pageCount")
	val pageCount: Int? = null,

	@Json(name="printType")
	val printType: String? = null,

	@Json(name="readingModes")
	val readingModes: ReadingModes? = null,

	@Json(name="previewLink")
	val previewLink: String? = null,

	@Json(name="canonicalVolumeLink")
	val canonicalVolumeLink: String? = null,

	@Json(name="description")
	val description: String? = null,

	@Json(name="language")
	val language: String? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="imageLinks")
	val imageLinks: ImageLinks? = null,

	@Json(name="subtitle")
	val subtitle: String? = null,

	@Json(name="averageRating")
	val averageRating: Double? = null,

	@Json(name="panelizationSummary")
	val panelizationSummary: PanelizationSummary? = null,

	@Json(name="publisher")
	val publisher: String? = null,

	@Json(name="ratingsCount")
	val ratingsCount: Int? = null,

	@Json(name="publishedDate")
	val publishedDate: String? = null,

	@Json(name="categories")
	val categories: List<String?>? = null,

	@Json(name="maturityRating")
	val maturityRating: String? = null,

	@Json(name="allowAnonLogging")
	val allowAnonLogging: Boolean? = null,

	@Json(name="contentVersion")
	val contentVersion: String? = null,

	@Json(name="authors")
	val authors: List<String?>? = null,

	@Json(name="infoLink")
	val infoLink: String? = null
) : Parcelable

@Parcelize
data class AccessInfo(

	@Json(name="accessViewStatus")
	val accessViewStatus: String? = null,

	@Json(name="country")
	val country: String? = null,

	@Json(name="viewability")
	val viewability: String? = null,

	@Json(name="pdf")
	val pdf: Pdf? = null,

	@Json(name="webReaderLink")
	val webReaderLink: String? = null,

	@Json(name="epub")
	val epub: Epub? = null,

	@Json(name="publicDomain")
	val publicDomain: Boolean? = null,

	@Json(name="quoteSharingAllowed")
	val quoteSharingAllowed: Boolean? = null,

	@Json(name="embeddable")
	val embeddable: Boolean? = null,

	@Json(name="textToSpeechPermission")
	val textToSpeechPermission: String? = null
) : Parcelable

@Parcelize
data class IndustryIdentifiersItem(

	@Json(name="identifier")
	val identifier: String? = null,

	@Json(name="type")
	val type: String? = null
) : Parcelable

@Parcelize
data class PanelizationSummary(

	@Json(name="containsImageBubbles")
	val containsImageBubbles: Boolean? = null,

	@Json(name="containsEpubBubbles")
	val containsEpubBubbles: Boolean? = null
) : Parcelable

@Parcelize
data class Epub(

	@Json(name="isAvailable")
	val isAvailable: Boolean? = null
) : Parcelable
