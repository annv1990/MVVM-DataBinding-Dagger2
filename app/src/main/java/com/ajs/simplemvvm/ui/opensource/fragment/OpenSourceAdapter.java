package com.ajs.simplemvvm.ui.opensource.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajs.simplemvvm.databinding.ItemOpenSourceBinding;
import com.ajs.simplemvvm.model.OpenSourceResponse;

import java.util.List;

public class OpenSourceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<OpenSourceItemViewModel> mList;

    public OpenSourceAdapter(List<OpenSourceItemViewModel> repos) {
        mList = repos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemOpenSourceBinding itemOpenSourceBinding = ItemOpenSourceBinding.inflate(
                LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new OpenSourceViewHolder(itemOpenSourceBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //TODO onbind
        ((OpenSourceViewHolder) viewHolder).onBind(i);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class OpenSourceViewHolder extends RecyclerView.ViewHolder {

        private final ItemOpenSourceBinding mBinding;


        public OpenSourceViewHolder(ItemOpenSourceBinding binding){
            super(binding.getRoot());
            mBinding = binding;
        }

        public void onBind(int position) {
            final OpenSourceItemViewModel viewModel = mList.get(position);
            mBinding.setOpenSourceIVM(viewModel);
        }

    }



}
