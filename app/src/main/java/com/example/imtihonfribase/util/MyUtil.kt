package com.example.imtihonfribase.util

import timber.log.Timber


fun String.myLog(tag: String = "TTT") = Timber.tag(tag).d(this)
