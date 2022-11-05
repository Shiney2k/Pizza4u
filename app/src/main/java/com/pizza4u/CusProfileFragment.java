package com.pizza4u;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class CusProfileFragment extends Fragment {

    private View view;
    private String name,phone,email;
    private EditText edt_name,edt_phone,edt_email;
    private Button btnSave;
    //private DatabaseHelper newDB; photo location

    public CusProfileFragment() {
        // Required empty public constructor
    }

    public static CusProfileFragment newInstance(String param1, String param2) {
        CusProfileFragment fragment = new CusProfileFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cus_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edt_name=view.findViewById(R.id.txtProfileName);
        edt_email=view.findViewById(R.id.txtCusMail);
        edt_phone=view.findViewById(R.id.txtCusPhone);
        btnSave=view.findViewById(R.id.btnSave);

        //newDB = new DatabaseHelper(MedicalNotesActivity.this);

//        Cursor cursor = newDB.displayNotes(Integer.valueOf(MainActivity.id.get(0)));
//            imgNoNotes.setVisibility(View.VISIBLE);
//            txtNoNotes.setVisibility(View.VISIBLE);
//        } else {
//
//                name.add(cursor.getString(0));
//                email.add(cursor.getString(1));
//                phone.add(cursor.getString(2));
//
//            imgNoNotes.setVisibility(View.GONE);
//            txtNoNotes.setVisibility(View.GONE);

        edt_name.setText(name);
        edt_email.setText(email);
        edt_phone.setText(phone);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}