package com.example.opaynhrms.di
 import com.example.opaynhrms.utils.NetworkCheck
 import com.example.opaynhrms.utils.SharedPreferenceManager
 import org.koin.dsl.module.module
import org.koin.experimental.builder.single
private val dataModule = module {

    single<NetworkCheck>()
    single<SharedPreferenceManager>()
 }
val appModules = listOf(dataModule)
