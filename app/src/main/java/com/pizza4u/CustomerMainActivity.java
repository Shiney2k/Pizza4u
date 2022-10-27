package com.pizza4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class CustomerMainActivity extends AppCompatActivity {

    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);
        replaceFragment(new CusHomeFragment());
        nav = findViewById(R.id.nav_bar);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        replaceFragment(new CusHomeFragment());
                        break;
                    case R.id.cart:
                        replaceFragment(new CusCartFragment());
                        break;
                    case R.id.orders:
                        replaceFragment(new CusOrdersFragment());
                        break;
                    case R.id.profile:
                        replaceFragment(new CusProfileFragment());
                        break;

                }
                return true;
            }
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_frame_container,fragment);
        fragmentTransaction.commit();
    }
}