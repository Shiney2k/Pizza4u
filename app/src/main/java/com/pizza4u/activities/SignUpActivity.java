package com.pizza4u.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.pizza4u.fragments.CustomerFragment;
import com.pizza4u.fragments.ManagerFragment;
import com.pizza4u.R;

public class SignUpActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);

        if(ContextCompat.checkSelfPermission(SignUpActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SignUpActivity.this,
                    new String[]{Manifest.permission.CAMERA}, 0);
        }

        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragment_container, new CustomerFragment()).commit();
    }

    private final NavigationBarView.OnItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;
        int itemId = item.getItemId();
        if (itemId == R.id.menuItemCustomer) {
            selectedFragment = new CustomerFragment();
        } else if (itemId == R.id.menuItemManager) {
            selectedFragment = new ManagerFragment();
        }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragment_container, selectedFragment).commit();
            }
            return true;
    };
}