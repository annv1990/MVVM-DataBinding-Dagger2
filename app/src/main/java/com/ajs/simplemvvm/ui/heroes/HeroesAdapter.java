package com.ajs.simplemvvm.ui.heroes;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ajs.simplemvvm.R;
import com.ajs.simplemvvm.databinding.ItemHeroBinding;
import com.ajs.simplemvvm.model.Hero;

import java.util.List;

public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.HeroViewHolder> {

    Context mCtx;
    List<Hero> heroList;

    public HeroesAdapter(Context mCtx, List<Hero> heroList) {
        this.mCtx = mCtx;
        this.heroList = heroList;
    }

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHeroBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(mCtx), R.layout.item_hero, parent, false);

        return new HeroViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroViewHolder holder, int position) {
        Hero hero = heroList.get(position);
        holder.binding.setHero(hero);
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    class HeroViewHolder extends RecyclerView.ViewHolder {

        ItemHeroBinding binding;

        public HeroViewHolder(final ItemHeroBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
