package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    ArrayList<infoModel> arrInfo = new ArrayList<>();
    adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recView);
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        arrInfo.add(new infoModel(R.drawable.a, "Kashifa", "1221234", "Ahmed garden"));
        arrInfo.add(new infoModel(R.drawable.b, "Hamza", "1221234", "Riddan"));
        arrInfo.add(new infoModel(R.drawable.c, "Alia", "1221234", "kusar colony"));
        arrInfo.add(new infoModel(R.drawable.d, "Raza", "1221234", "Setlite town"));
        arrInfo.add(new infoModel(R.drawable.e, "Kashif", "1221234", "Khawja town"));
        arrInfo.add(new infoModel(R.drawable.f, "Waj", "1221234", "Hashmi garden"));
        arrInfo.add(new infoModel(R.drawable.g, "Asghar", "1221234", "Al noor garden"));
        arrInfo.add(new infoModel(R.drawable.h, "Amin", "1221234", "Model town A"));
        arrInfo.add(new infoModel(R.drawable.i, "Robot", "1221234", "Shahdra Chowk"));
        arrInfo.add(new infoModel(R.drawable.j, "Nadir", "1221234", "Defence Housing Authurity"));
        arrInfo.add(new infoModel(R.drawable.k, "Huma", "1221234", "Japan Town"));

        adapter adapter = new adapter(this, arrInfo);
        recyclerView.setAdapter(adapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_update);

                EditText editName = dialog.findViewById(R.id.edtName);
                EditText editPhone = dialog.findViewById(R.id.edtNumber);
                EditText editAddress = dialog.findViewById(R.id.edtAddress);
                Button addBtn = dialog.findViewById(R.id.btnAction);

                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = editName.getText().toString();
                        String number = editPhone.getText().toString();
                        String address = editAddress.getText().toString();

                        if (name.isEmpty() || number.isEmpty() || address.isEmpty()) {
                            Toast.makeText(MainActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                        } else {
                            arrInfo.add(new infoModel(name, number, address));
                            adapter.notifyItemInserted(arrInfo.size() - 1);
                            recyclerView.scrollToPosition(arrInfo.size() - 1);
                            dialog.dismiss();
                        }
                    }
                });

                dialog.show();
            }
        });



    }
}