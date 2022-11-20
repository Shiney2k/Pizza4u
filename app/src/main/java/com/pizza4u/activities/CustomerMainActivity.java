package com.pizza4u.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pizza4u.fragments.CusCartFragment;
import com.pizza4u.fragments.CusHomeFragment;
import com.pizza4u.fragments.CusOrdersFragment;
import com.pizza4u.fragments.CusProfileFragment;
import com.pizza4u.R;
import com.pizza4u.models.CartItemModel;
import com.pizza4u.models.OrderItemModel;
import com.pizza4u.models.OrderModel;
import com.pizza4u.models.PizzaModel;
import com.pizza4u.models.PizzaTypeModel;
import com.pizza4u.models.UserModel;

import java.util.ArrayList;

public class CustomerMainActivity extends AppCompatActivity {

    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        UserModel userModel = (UserModel) getIntent().getSerializableExtra("userData");
        Log.d("UserData from Customer Home", userModel.getEmail() + " " + userModel.getFname());

        Bundle bundleh = new Bundle();
        bundleh.putSerializable("userModel", userModel);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.nav_frame_container, CusHomeFragment.class, bundleh)
                .commit();

        nav = findViewById(R.id.nav_bar);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Bundle bundleh = new Bundle();
                        bundleh.putSerializable("userModel", userModel);

                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.nav_frame_container, CusHomeFragment.class, bundleh)
                                .commit();
                        break;
                    case R.id.cart:
                        Bundle bundlec = new Bundle();
                        bundlec.putSerializable("userModel", userModel);

                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.nav_frame_container, CusCartFragment.class, bundlec)
                                .commit();
                        break;
                    case R.id.orders:
                        Bundle bundleo = new Bundle();
                        bundleo.putSerializable("userModel", userModel);

                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.nav_frame_container, CusOrdersFragment.class, bundleo)
                                .commit();
                        break;
                    case R.id.profile:
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("userModel", userModel);

                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.nav_frame_container, CusProfileFragment.class, bundle)
                                .commit();
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