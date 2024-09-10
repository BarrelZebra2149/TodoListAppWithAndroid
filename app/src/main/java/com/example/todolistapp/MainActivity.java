package com.example.todolistapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<String> itemList;
    private Button addButton, removeButton;

    private static final int REQUEST_CODE = 1; // Request code for result
    private static final int RESULT_DELETE = SubActivity.RESULT_DELETE; // Result code for delete
    private static final int RESULT_LIST = 2; // Custom result code for list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = new ArrayList<>();
        itemList.add("자바 공부하기");
        itemList.add("운동하고 놀기");
        itemList.add("산책하기");
        itemList.add("밥하고 빨리하고 청소하기");

        recyclerView = findViewById(R.id.recycler_view);
        addButton = findViewById(R.id.add_button);
        removeButton = findViewById(R.id.remove_button);

        adapter = new MyAdapter(this, itemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem("New Item " + (itemList.size() + 1));
                Toast.makeText(MainActivity.this, "Item Added", Toast.LENGTH_SHORT).show();
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!itemList.isEmpty()) {
                    adapter.removeItem(0);
                    Toast.makeText(MainActivity.this, "Item Removed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "No Items to Remove", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String updatedText = data.getStringExtra("updatedText");
                int position = data.getIntExtra("itemPosition", -1);

                if (position != -1) {
                    adapter.updateItem(position, updatedText);
                }
            } else if (resultCode == RESULT_DELETE) {
                int position = data.getIntExtra("itemPosition", -1);

                if (position != -1) {
                    adapter.removeItem(position);
                }
            } else if (resultCode == RESULT_LIST) {
                // Handle the custom result code for the "List" button if needed
                // For now, no action is needed here
            }
        }
    }
}
