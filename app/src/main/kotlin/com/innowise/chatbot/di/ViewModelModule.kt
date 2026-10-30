package com.innowise.chatbot.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.innowise.chatbot.ui.screen.chat.ChatViewModel
import com.innowise.chatbot.ui.screen.test.TestViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TestViewModel::class)
    abstract fun bindTestViewModel(viewModel: TestViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChatViewModel::class)
    abstract fun bindChatViewModel(viewModel: ChatViewModel): ViewModel
}
