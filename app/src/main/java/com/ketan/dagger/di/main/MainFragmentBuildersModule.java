package com.ketan.dagger.di.main;

import com.ketan.dagger.ui.main.posts.PostsFragment;
import com.ketan.dagger.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector/*(
            modules = {
                    //mod.class
            }
    )*/
    abstract PostsFragment contriburePostFragment();
    // maybe for othe fragment
}
