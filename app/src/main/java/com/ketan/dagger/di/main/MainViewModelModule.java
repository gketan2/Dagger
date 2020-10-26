package com.ketan.dagger.di.main;

import androidx.lifecycle.ViewModel;

import com.ketan.dagger.di.ViewModelKey;
import com.ketan.dagger.ui.main.posts.PostsViewModel;
import com.ketan.dagger.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel profileViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    public abstract ViewModel bindPostsViewModel(PostsViewModel postViewModel);
}