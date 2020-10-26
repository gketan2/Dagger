package com.ketan.dagger.di;

import com.ketan.dagger.di.auth.AuthModule;
import com.ketan.dagger.di.auth.AuthViewModelModule;
import com.ketan.dagger.di.main.MainFragmentBuildersModule;
import com.ketan.dagger.di.main.MainModule;
import com.ketan.dagger.di.main.MainViewModelModule;
import com.ketan.dagger.ui.auth.AuthActivity;
import com.ketan.dagger.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelModule.class,
                    AuthModule.class,
            }
    )
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector(
            modules = {
                    MainFragmentBuildersModule.class,
                    MainViewModelModule.class,
                    MainModule.class
            }
    )
    abstract MainActivity contributeMainActivity();
}
