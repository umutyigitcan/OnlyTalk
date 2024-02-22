package com.example.onlytalk

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class KullaniciKayit(var kullaniciadi:String?="",var kullanicisifre:String?="") {
}