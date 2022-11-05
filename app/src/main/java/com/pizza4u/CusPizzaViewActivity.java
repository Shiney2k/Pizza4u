package com.pizza4u;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CusPizzaViewActivity extends AppCompatActivity {

    private TextView txtName,txtPrice,txtDescription;
    private Button btn_addCart;
    private String name,price,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_pizza_view);

        txtName = findViewById(R.id.txtPizzaName);
        txtDescription=findViewById(R.id.textDescription_pizzaView);
        txtPrice=findViewById(R.id.txtPizzaPrice);
        btn_addCart=findViewById(R.id.btn_addtoCart);

        name=getIntent().getStringExtra("name");
        price=getIntent().getStringExtra("price");
        description=getIntent().getStringExtra("description");

        txtName.setText(name);
        txtPrice.setText(price);
        txtDescription.setText(description);

        btn_addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                newDB = new DatabaseHelper(CusPizzaViewActivity.this);
//                newDB.updateNote(Integer.valueOf(id),
//                        edttxtTitle.getText().toString().trim(),
//                        edttxtCategory.getText().toString().trim(),
//                        edttxtNote.getText().toString().trim());
            }
        });
    }
}