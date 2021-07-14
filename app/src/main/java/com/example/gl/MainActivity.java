package com.example.gl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.example.gl.fragment.AboutUsFragments;
import com.example.gl.fragment.AllGamesFragments;
import com.example.gl.fragment.CoomingSoonFragments;
import com.example.gl.fragment.MainMenuFragments;
import com.example.gl.fragment.SearchFragments;
import com.example.gl.fragment.TopGamesFragments;
import com.example.gl.fragment.TrailerFragments;
import com.example.gl.mainActivity.ManagerApp;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout m_DrawerLayout;
    private NavigationView m_NavigationView;
    private final ManagerApp m_ManagerApp = new ManagerApp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        m_DrawerLayout = findViewById(R.id.drawer_layout);
        m_NavigationView = findViewById(R.id.Navigator_view);
        m_NavigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                m_DrawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        m_DrawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        if(savedInstanceState == null){

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainMenuFragments()).addToBackStack(null).commit();

        }

    }


    @Override
    public void onBackPressed()
    {

        if (m_DrawerLayout.isDrawerOpen(GravityCompat.START)){

            m_DrawerLayout.closeDrawer(GravityCompat.START);

        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {

        switch (item.getItemId()){

            case R.id.navigator_main_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  m_ManagerApp.GetMainMenu()).addToBackStack(null).commit();
                break;
            case R.id.navigator_all_games:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,   m_ManagerApp.GetGames()).addToBackStack(null).commit();
                break;
            case R.id.navigator_search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  m_ManagerApp.GetSearch()).addToBackStack(null).commit();
                break;
            case R.id.navigator_top_games:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, m_ManagerApp.GetTopGames()).addToBackStack(null).commit();
                break;
            case R.id.navigator_cooming_soon:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  m_ManagerApp.GetCoomingSoon()).addToBackStack(null).commit();
                break;
            case R.id.navigator_trailer:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  m_ManagerApp.GetTrailer()).addToBackStack(null).commit();
                break;
            case R.id.navigator_about_us:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, m_ManagerApp.GetAboutUs()).addToBackStack(null).commit();
                break;

        }

        m_DrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}