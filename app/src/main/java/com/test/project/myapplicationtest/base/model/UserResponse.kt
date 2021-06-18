package com.test.project.myapplicationtest.base

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


/**Raw
 * Created by Harshal Chaudhari on 16/06/21.
 */

@Parcelize
data class UserResponse(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: List<Result>
) : Parcelable

@Parcelize
data class Info(
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("results")
    val results: Int, // 10
    @SerializedName("seed")
    val seed: String?, // c8119d9c04b9754a
    @SerializedName("version")
    val version: String? // 1.3
) : Parcelable

@Parcelize
data class Result(
    @SerializedName("cell")
    val cell: String?, // 081-966-4400
    @SerializedName("dob")
    val dob: Dob?,
    @SerializedName("email")
    val email: String?, // dylan.davies@example.com
    @SerializedName("gender")
    val gender: String?, // male
    @SerializedName("id")
    val id: Id?,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("login")
    val login: Login?,
    @SerializedName("name")
    val name: Name?,
    @SerializedName("nat")
    val nat: String?, // IE
    @SerializedName("phone")
    val phone: String?, // 011-915-6363
    @SerializedName("picture")
    val picture: Picture?,
    @SerializedName("registered")
    val registered: Registered?,
    var liked: Boolean = false
) : Parcelable

@Parcelize
data class Dob(
    @SerializedName("age")
    val age: Int, // 46
    @SerializedName("date")
    val date: String? // 1975-03-03T21:44:49.560Z
) : Parcelable

@Parcelize
data class Id(
    @SerializedName("name")
    val name: String?, // PPS
    @SerializedName("value")
    val value: String? // 7463369T
) : Parcelable

@Parcelize
data class Location(
    @SerializedName("city")
    val city: String?, // Killarney
    @SerializedName("coordinates")
    val coordinates: Coordinates?,
    @SerializedName("country")
    val country: String?, // Ireland
    @SerializedName("postcode")
    val postcode: @RawValue Any?, // null
    @SerializedName("state")
    val state: String?, // Westmeath
    @SerializedName("street")
    val street: Street?,
    @SerializedName("timezone")
    val timezone: Timezone?
) : Parcelable

@Parcelize
data class Login(
    @SerializedName("md5")
    val md5: String?, // 9c03570e60bb45d42e85814feb5821a1
    @SerializedName("password")
    val password: String?, // trance
    @SerializedName("salt")
    val salt: String?, // RZH9eotS
    @SerializedName("sha1")
    val sha1: String?, // 51da0ff579aeca1066786ac7452450fe1f7a7e00
    @SerializedName("sha256")
    val sha256: String?, // dd0de4bb057704246539ffa2bd6ccb771af3906fd50b1eee79cfe6d220958fec
    @SerializedName("username")
    val username: String?, // browncat793
    @SerializedName("uuid")
    val uuid: String? // 43c697e5-bd8f-4135-be32-c97d0e1996a5
) : Parcelable

@Parcelize
data class Name(
    @SerializedName("first")
    val first: String?, // Dylan
    @SerializedName("last")
    val last: String?, // Davies
    @SerializedName("title")
    val title: String? // Mr
) : Parcelable

@Parcelize
data class Picture(
    @SerializedName("large")
    val large: String?, // https://randomuser.me/api/portraits/men/85.jpg
    @SerializedName("medium")
    val medium: String?, // https://randomuser.me/api/portraits/med/men/85.jpg
    @SerializedName("thumbnail")
    val thumbnail: String? // https://randomuser.me/api/portraits/thumb/men/85.jpg
) : Parcelable

@Parcelize
data class Registered(
    @SerializedName("age")
    val age: Int, // 11
    @SerializedName("date")
    val date: String? // 2010-07-13T21:25:28.645Z
) : Parcelable

@Parcelize
data class Coordinates(
    @SerializedName("latitude")
    val latitude: String?, // 18.5926
    @SerializedName("longitude")
    val longitude: String? // -38.6762
) : Parcelable

@Parcelize
data class Street(
    @SerializedName("name")
    val name: String?, // Park Avenue
    @SerializedName("number")
    val number: Int // 4251
) : Parcelable

@Parcelize
data class Timezone(
    @SerializedName("description")
    val description: String?, // Kathmandu
    @SerializedName("offset")
    val offset: String? // +5:45
) : Parcelable