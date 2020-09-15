package com.resende.todolist

import android.app.Application
import com.resende.todolist.di.module.AppModule
import com.resende.todolist.di.module.NetworkModule
import com.resende.todolist.di.module.dbModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class TodoApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TodoApplication)
            modules(listOf(AppModule, dbModule, NetworkModule))
        }
    }
}