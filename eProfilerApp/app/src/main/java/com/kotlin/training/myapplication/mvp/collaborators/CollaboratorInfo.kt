package com.kotlin.training.myapplication.mvp.collaborators

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class UserResponse (
    @SerializedName("results")
    var users: ArrayList<User>
)

@Parcelize
data class User (
    @SerializedName("name")
    var name : UserName = UserName(),
    @SerializedName("picture")
    var picture: UserPicture = UserPicture(),
    @SerializedName("email")
    var email: String = "",
    @SerializedName("location")
    var location: Location = Location(),
    @SerializedName("nat")
    var nationality: String = ""
): Parcelable

@Parcelize
data class UserName (
    @SerializedName("title")
    var title: String= "",
    @SerializedName("first")
    var first: String = "",
    @SerializedName("last")
    var last: String = ""
): Parcelable

@Parcelize
data class Location (
    @SerializedName("street")
    var street: String = "",
    @SerializedName("city")
    var city: String = "",
    @SerializedName("state")
    var state: String = "",
    @SerializedName("postcode")
    var postcode: String = ""
): Parcelable {
    fun address(): String =
            "$street, $city, $state, $postcode"
}

@Parcelize
data class UserPicture (
    @SerializedName("large")
    var large: String= "",
    @SerializedName("medium")
    var medium: String= "",
    @SerializedName("thumbnail")
    var thumbnail: String= ""
): Parcelable