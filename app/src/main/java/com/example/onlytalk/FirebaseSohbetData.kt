package com.example.onlytalk

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class FirebaseSohbetData(var mesaj:String?="",var kullanici:String?="") {
}