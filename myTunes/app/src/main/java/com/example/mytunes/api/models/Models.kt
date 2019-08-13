package com.example.mytunes.api.models

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ITunesSearchResponse(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val movies: List<Movie>
)

@Entity(tableName = "movies_table")
@Parcelize
data class Movie(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @SerializedName("amgArtistId")
    val amgArtistId: Int?,
    @SerializedName("artistId")
    val artistId: Int?,
    @SerializedName("artistName")
    val artistName: String?,
    @SerializedName("artistViewUrl")
    val artistViewUrl: String?,
    @SerializedName("artworkUrl100")
    val artworkUrl100: String?,
    @SerializedName("artworkUrl30")
    val artworkUrl30: String?,
    @SerializedName("artworkUrl60")
    val artworkUrl60: String?,
    @SerializedName("artworkUrl600")
    val artworkUrl600: String?,
    @SerializedName("collectionArtistId")
    val collectionArtistId: Int?,
    @SerializedName("collectionArtistName")
    val collectionArtistName: String?,
    @SerializedName("collectionArtistViewUrl")
    val collectionArtistViewUrl: String?,
    @SerializedName("collectionCensoredName")
    val collectionCensoredName: String?,
    @SerializedName("collectionExplicitness")
    val collectionExplicitness: String?,
    @SerializedName("collectionHdPrice")
    val collectionHdPrice: Double?,
    @SerializedName("collectionId")
    val collectionId: Int?,
    @SerializedName("collectionName")
    val collectionName: String?,
    @SerializedName("collectionPrice")
    val collectionPrice: Double?,
    @SerializedName("collectionViewUrl")
    val collectionViewUrl: String?,
    @SerializedName("contentAdvisoryRating")
    val contentAdvisoryRating: String?,
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("discCount")
    val discCount: Int?,
    @SerializedName("discNumber")
    val discNumber: Int?,
    @SerializedName("feedUrl")
    val feedUrl: String?,
    @SerializedName("genreIds")
    val genreIds: List<String>?,
    @SerializedName("genres")
    val genres: List<String>?,
    @SerializedName("hasITunesExtras")
    val hasITunesExtras: Boolean?,
    @SerializedName("isStreamable")
    val isStreamable: Boolean?,
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("longDescription")
    val longDescription: String?,
    @SerializedName("previewUrl")
    val previewUrl: String?,
    @SerializedName("primaryGenreName")
    val primaryGenreName: String?,
    @SerializedName("releaseDate")
    val releaseDate: String?,
    @SerializedName("shortDescription")
    val shortDescription: String?,
    @SerializedName("trackCensoredName")
    val trackCensoredName: String?,
    @SerializedName("trackCount")
    val trackCount: Int?,
    @SerializedName("trackExplicitness")
    val trackExplicitness: String?,
    @SerializedName("trackHdPrice")
    val trackHdPrice: Double?,
    @SerializedName("trackHdRentalPrice")
    val trackHdRentalPrice: Double?,
    @SerializedName("trackId")
    val trackId: Int?,
    @SerializedName("trackName")
    val trackName: String?,
    @SerializedName("trackNumber")
    val trackNumber: Int?,
    @SerializedName("trackPrice")
    val trackPrice: Double?,
    @SerializedName("trackRentalPrice")
    val trackRentalPrice: Double?,
    @SerializedName("trackTimeMillis")
    val trackTimeMillis: Int?,
    @SerializedName("trackViewUrl")
    val trackViewUrl: String?,
    @SerializedName("wrapperType")
    val wrapperType: String?
) : Parcelable