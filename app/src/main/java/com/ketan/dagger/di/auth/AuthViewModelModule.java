package com.ketan.dagger.di.auth;

import androidx.lifecycle.ViewModel;

import com.ketan.dagger.di.ViewModelKey;
import com.ketan.dagger.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}
