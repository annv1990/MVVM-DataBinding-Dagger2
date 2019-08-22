package com.ajs.simplemvvm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ajs.simplemvvm.base.ClickHandler;
import com.ajs.simplemvvm.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mMainBinding;
    RecyclerView recyclerView;
    HeroesAdapter adapter;
    ClickHandler mClickHandler;

    List<Hero> heroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mClickHandler = new ClickHandler(this);
        mMainBinding.setClickHandler(mClickHandler);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        HeroesViewModel model = ViewModelProviders.of(this).get(HeroesViewModel.class);

        model.getHeroes().observe(this, new Observer<List<Hero>>() {
            @Override
            public void onChanged(@Nullable List<Hero> heroes) {
                adapter = new HeroesAdapter(MainActivity.this, heroes);
                recyclerView.setAdapter(adapter);
                mMainBinding.setFavoriteHero(heroes.get(0));
            }
        });
    }
}
