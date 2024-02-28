package com.example.onlytalk

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class FirebaseSohbetBaslatmaData(var isim:String?="",var resim:Int?=0,var saat:String?="") {
}