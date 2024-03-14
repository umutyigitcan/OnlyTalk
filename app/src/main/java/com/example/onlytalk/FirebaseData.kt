package com.example.onlytalk

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class FirebaseData(var mesaj:String?="",var kullanici:String?="") {
}