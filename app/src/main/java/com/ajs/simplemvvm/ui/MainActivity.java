package com.ajs.simplemvvm.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ajs.simplemvvm.BR;
import com.ajs.simplemvvm.R;
import com.ajs.simplemvvm.base.BaseActivity;
import com.ajs.simplemvvm.databinding.ActivityMain2Binding;
import com.ajs.simplemvvm.databinding.ActivityMainBinding;
import com.ajs.simplemvvm.menu.Main2Activity;
import com.ajs.simplemvvm.model.Hero;
import com.ajs.simplemvvm.ui.blog.BlogActivity;
import com.ajs.simplemvvm.ui.heroes.HeroesAdapter;
import com.ajs.simplemvvm.ui.heroes.HeroesViewModel;
import com.ajs.simplemvvm.ui.home.HomeActivity;
import com.ajs.simplemvvm.ui.opensource.OpenSourceActivity;
import com.crashlytics.android.Crashlytics;

import java.util.List;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends BaseActivity<ActivityMainBinding, HeroesViewModel>
        implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    ActivityMain2Binding mMainBinding;
    RecyclerView recyclerView;
    HeroesAdapter adapter;
    HeroesViewModel heroesViewModel;
    Button btnNext;
    Button btnNextFragment;
    Button btnHome;
    Button btnMenu;
    Button btnSlideMenuOpen;
    Button btnSlideMenuClose;
    DrawerLayout mDrawerLayout;
    LinearLayout llM1;
    LinearLayout llM2;
    LinearLayout llM3;
;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        bindView();

        ((HeroesViewModel) getViewModel()).getHeroes().observe(this, new Observer<List<Hero>>() {
            @Override
            public void onChanged(@Nullable List<Hero> heroes) {
                adapter = new HeroesAdapter(MainActivity.this, heroes);
                recyclerView.setAdapter(adapter);
//                mMainBinding.setFavoriteHero(heroes.get(0));
//                mMainBinding.mainView.setFavoriteHero(heroes.get(0));
            }

        });
    }

    private void bindView() {
        btnNext = findViewById(R.id.btnNext);
        btnNextFragment = findViewById(R.id.btnNextFragment);
        btnNext.setOnClickListener(this);
        btnNextFragment.setOnClickListener(this);
        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(this);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(this);
        btnSlideMenuOpen = findViewById(R.id.btnSlideMenuOpen);
        btnSlideMenuOpen.setOnClickListener(this);
        llM1 = findViewById(R.id.llM1);
        llM1.setOnClickListener(this);
        llM2 = findViewById(R.id.llM2);
        llM2.setOnClickListener(this);
        llM3 = findViewById(R.id.llM3);
        llM3.setOnClickListener(this);
        btnSlideMenuClose = findViewById(R.id.btnSlideMenuClose);
        btnSlideMenuClose.setOnClickListener(this);

        mDrawerLayout = findViewById(R.id.drawer_layout);

    }

    @Override
    protected int getResourceLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public HeroesViewModel getViewModel() {
        if (heroesViewModel == null)
            heroesViewModel = ViewModelProviders.of(this).get(HeroesViewModel.class);
        return heroesViewModel;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext: {
                Intent i = new Intent(MainActivity.this, BlogActivity.class);
                startActivity(i);
                break;
            }
            case R.id.btnNextFragment: {
                Intent i = new Intent(MainActivity.this, OpenSourceActivity.class);
                startActivity(i);
                break;
            }

            case R.id.btnHome: {
                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(i);
                break;
            }

            case R.id.btnMenu: {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
                break;
            }

            case R.id.btnSlideMenuOpen: {
                mDrawerLayout.openDrawer(Gravity.START);
                break;
            }

            case R.id.btnSlideMenuClose: {
                mDrawerLayout.closeDrawer(Gravity.START);
                break;
            }

            case R.id.llM1: {
                mDrawerLayout.closeDrawer(Gravity.START);
                break;
            }

            case R.id.llM2: {
                mDrawerLayout.closeDrawer(Gravity.START);
                break;
            }

            case R.id.llM3: {
                mDrawerLayout.closeDrawer(Gravity.START);
                break;
            }

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
