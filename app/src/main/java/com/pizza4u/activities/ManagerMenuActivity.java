package com.pizza4u.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.pizza4u.R;
import com.pizza4u.fragments.CusCartFragment;
import com.pizza4u.fragments.CusHomeFragment;
import com.pizza4u.fragments.CusOrdersFragment;
import com.pizza4u.fragments.CusProfileFragment;
import com.pizza4u.fragments.ManagerPizzaTypesFragment;
import com.pizza4u.fragments.ManagerPizzasFragment;

public class ManagerMenuActivity extends AppCompatActivity {

    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_menu);

        replaceFragment(new ManagerPizzaTypesFragment());

        nav = findViewById(R.id.nav_bar_manager_menu);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.types:
                        replaceFragment(new ManagerPizzaTypesFragment());
                        break;
                    case R.id.pizzas:
                        replaceFragment(new ManagerPizzasFragment());
                        break;
                }
                return true;
            }
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_frame_container_manager_menu,fragment);
        fragmentTransaction.commit();
    }

}