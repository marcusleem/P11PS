package com.android.p11ps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private String[] drawerItems;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    FloatingActionButton fab;
    ArrayAdapter<String> aa;
    String currentTitle;
    ActionBar ab;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerList = findViewById(R.id.left_drawer);
        fab = findViewById(R.id.fab);

        currentTitle = this.getTitle().toString();

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                ab.setTitle(currentTitle);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ab.setTitle("Make a selection");
            }
        };


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerList);
            }
        });

        drawerItems = new String[] { "Bio", "Vaccination", "Anniversary", "About Us" };
        ab = getSupportActionBar();
        drawerLayout.addDrawerListener(drawerToggle);
        ab.setDisplayHomeAsUpEnabled(true);
        aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, drawerItems);
        drawerList.setAdapter(aa);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Fragment fragment = null;
                if (position == 0)
                    fragment = new BioFragment();
                else if (position == 1)
                    fragment = new VaccinationFragment();
                else if (position == 2)
                    fragment = new AnniversaryFragment();
                else if (position == 3){
                    Intent i = new Intent(MainActivity.this, AboutUs.class);
                    startActivity(i);
                    return;
                }




                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction trans = fm.beginTransaction();
                trans.replace(R.id.content_frame, fragment);
                trans.commit();


                drawerList.setItemChecked(position, true);
                currentTitle = drawerItems[position];
                ab.setTitle(currentTitle);
                drawerLayout.closeDrawer(drawerList);
            }


        });



    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}
