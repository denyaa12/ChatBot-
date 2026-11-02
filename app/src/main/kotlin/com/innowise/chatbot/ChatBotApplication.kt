package com.innowise.chatbot

import android.app.Application
import com.innowise.chatbot.di.AppComponent
import com.innowise.chatbot.di.DaggerAppComponent

class ChatBotApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}
