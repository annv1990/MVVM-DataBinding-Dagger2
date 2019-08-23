package com.ajs.simplemvvm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.ajs.simplemvvm.base.BaseActivity;
import com.ajs.simplemvvm.base.ClickHandler;
import com.ajs.simplemvvm.databinding.ActivityMainBinding;
import com.ajs.simplemvvm.ui.blog.BlogActivity;
import com.ajs.simplemvvm.ui.blog.BlogViewModel;

import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, HeroesViewModel> implements View.OnClickListener {

    ActivityMainBinding mMainBinding;
    RecyclerView recyclerView;
    HeroesAdapter adapter;
    ClickHandler mClickHandler;
    HeroesViewModel heroesViewModel;
    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        mClickHandler = new ClickHandler(this);
//        mMainBinding.setClickHandler(mClickHandler);
        mMainBinding = getViewDataBinding();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ((HeroesViewModel)getViewModel()).getHeroes().observe(this, new Observer<List<Hero>>() {
            @Override
            public void onChanged(@Nullable List<Hero> heroes) {
                adapter = new HeroesAdapter(MainActivity.this, heroes);
                recyclerView.setAdapter(adapter);
                mMainBinding.setFavoriteHero(heroes.get(0));
            }
        });
    }

    @Override
    protected int getResourceLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public ActivityMainBinding getViewDataBinding() {
        return super.getViewDataBinding();
    }

    @Override
    public HeroesViewModel getViewModel() {
        if(heroesViewModel == null)
            heroesViewModel = ViewModelProviders.of(this).get(HeroesViewModel.class);
        return heroesViewModel;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext : {
                Intent i = new Intent(MainActivity.this, BlogActivity.class);
                startActivity(i);
                break;
            }
        }
    }
}
