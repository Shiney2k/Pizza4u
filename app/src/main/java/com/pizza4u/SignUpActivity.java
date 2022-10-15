package com.pizza4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SignUpActivity extends AppCompatActivity {

    EditText branchId;
    EditText employeeId;
    Button addProfilePicture;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        branchId = findViewById(R.id.editTextBranchId);
        employeeId = findViewById(R.id.editTextEmployeeId);
        addProfilePicture = findViewById(R.id.buttonAddProfilePicture);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CustomerFragment()).commit();
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;
        int itemId = item.getItemId();
        if (itemId == R.id.menuItemCustomer) {
            selectedFragment = new CustomerFragment();
        } else if (itemId == R.id.menuItemManager) {
            selectedFragment = new ManagerFragment();
        }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            }
            return true;
    };
}