package com.ketan.dagger.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ketan.dagger.R;
import com.ketan.dagger.datamodels.Post;
import com.ketan.dagger.util.VerticalSpacingItemDecoration;
import com.ketan.dagger.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostsFragment extends DaggerFragment {

    private PostsViewModel postsViewModel;
    private RecyclerView recyclerView;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    PostRecyclerAdapter postRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);
        postsViewModel = new ViewModelProvider(this, providerFactory).get(PostsViewModel.class);
        initRecyclerView();
        subscribeObserver();
    }

    private void subscribeObserver(){
        postsViewModel.observerPosts().removeObservers(getViewLifecycleOwner());
        postsViewModel.observerPosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource){
                if(listResource != null){
                    switch (listResource.status){
                        case SUCCESS:{
                            System.out.println(listResource.data);
                            postRecyclerAdapter.setPosts(listResource.data);
                            break;
                        }
                        case LOADING:{
                            System.out.println("loading");
                            break;
                        }
                        case ERROR:{
                            Log.e("TAG", listResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpacingItemDecoration itemDecoration = new VerticalSpacingItemDecoration(15);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(postRecyclerAdapter);
    }
}