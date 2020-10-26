package com.ketan.dagger.ui.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ketan.dagger.R;
import com.ketan.dagger.datamodels.User;
import com.ketan.dagger.ui.auth.AuthResource;
import com.ketan.dagger.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {

    private ProfileViewModel profileViewModel;
    private TextView email, username, website;

    @Inject
    public ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(this, providerFactory).get(ProfileViewModel.class);
        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        website = view.findViewById(R.id.website);
        subscribeObservers();
    }

    private void subscribeObservers(){
        profileViewModel.getAuthenticatedUser().removeObservers(getViewLifecycleOwner());
        profileViewModel.getAuthenticatedUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource != null){
                    switch(userAuthResource.Status){
                        case AUTHENTICATED:{
                            setUserDetail(userAuthResource.data);
                            break;
                        }
                        case ERROR:{
                            setErrorDetail(userAuthResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void setUserDetail(User user){
        email.setText(user.getEmail());
        username.setText(user.getUsername());
        website.setText(user.getWebsite());
    }

    private void setErrorDetail(String message){
        email.setText(message);
        username.setText("error");
        username.setText("error");
    }
}