package com.ajs.simplemvvm.ui.opensource.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ajs.simplemvvm.model.OpenSourceResponse;

import java.util.List;

public class OpenSourceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<OpenSourceResponse.Repo> mRepos;

    public OpenSourceAdapter(List<OpenSourceResponse.Repo> repos) {
       mRepos = repos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mRepos.size();
    }

    public class OpenSourceViewHolder extends RecyclerView.ViewHolder {

        public OpenSourceViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }

}
