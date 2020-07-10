package com.embibe.tmdbapp.db;


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity;


import com.google.gson.annotations.SerializedName;

//,@SerializedName("geoLocation") val geoLocation:Geolocation?
//,parcel.readParcelable(
//Geolocation::class.java.getClassLoader()
@Entity(primaryKeys = ["id"])
data class BookMarkedMovie(@SerializedName("id")
                         val id: String, @SerializedName("title") val title:String?, @SerializedName("photo_path")val photo_path:String?):Parcelable{
                                 constructor(parcel: Parcel) : this(parcel.readString() as String,parcel.readString(),parcel.readString()) {
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
                val CREATOR: Parcelable.Creator<BookMarkedMovie>                               =   object : Parcelable.Creator<BookMarkedMovie> {
                        override fun createFromParcel(parcel: Parcel): BookMarkedMovie         =   BookMarkedMovie(parcel)
                        override fun newArray(size: Int): Array<BookMarkedMovie?>              =   arrayOfNulls(size)
                }
        }

}