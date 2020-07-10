package com.embibe.tmdbapp.db;


import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity;
import com.embibe.tmdbapp.service.models.Movie


import com.google.gson.annotations.SerializedName;

//,@SerializedName("geoLocation") val geoLocation:Geolocation?
//,parcel.readParcelable(
//Geolocation::class.java.getClassLoader()
@Entity(primaryKeys = ["profile_id"])
data class BookMarkedPhotos(@SerializedName("id")
                         val id: String?, @SerializedName("title") val title:String?,@SerializedName("photo_path")val photo_path:String?):Parcelable{
                                 constructor(parcel: Parcel) : this(parcel.readString(),parcel.readString(),parcel.readString()) {
                                 }

                                 override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
                                         writeString(title)
                                         writeString(id)
                                         writeString(photo_path)
                          }

        override fun describeContents(): Int {
                return 0
        }

        companion object {
                @JvmField
                val CREATOR: Parcelable.Creator<BookMarkedPhotos>                               =   object : Parcelable.Creator<BookMarkedPhotos> {
                        override fun createFromParcel(parcel: Parcel): BookMarkedPhotos         =   BookMarkedPhotos(parcel)
                        override fun newArray(size: Int): Array<BookMarkedPhotos?>              =   arrayOfNulls(size)
                }
        }

}