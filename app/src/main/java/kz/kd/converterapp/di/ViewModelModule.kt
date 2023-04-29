package kz.kd.converterapp.di

import kz.kd.converterapp.presentation.account.AccountViewModel
import kz.kd.converterapp.presentation.chat.ChatViewModel
import kz.kd.converterapp.presentation.converter.ConverterViewModel
import kz.kd.converterapp.presentation.favorites.FavoritesViewModel
import kz.kd.converterapp.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AccountViewModel() }
    viewModel { ChatViewModel() }
    viewModel { ConverterViewModel() }
    viewModel { FavoritesViewModel() }
    viewModel { SearchViewModel() }
}