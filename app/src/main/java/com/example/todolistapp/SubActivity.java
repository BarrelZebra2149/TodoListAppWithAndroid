package com.example.todolistapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {
    private EditText editText;
    private Button editButton, deleteButton, listButton;
    private int position; // Store the position of the item

    public static final int RESULT_DELETE = 3;
    public static final int RESULT_LIST = 2; // Custom result code for the "List" button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_activity_layout);

        editText = findViewById(R.id.edit_text);
        editButton = findViewById(R.id.edit_button);
        deleteButton = findViewById(R.id.delete_button);
        listButton = findViewById(R.id.list_button);

        String text = getIntent().getStringExtra("itemTitle");
        position = getIntent().getIntExtra("itemPosition", -1);
        editText.setText(text);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("updatedText", editText.getText().toString());
                resultIntent.putExtra("itemPosition", position);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("itemPosition", position);
                setResult(RESULT_DELETE, resultIntent); // Use a custom result code
                finish();
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("itemPosition", -1);
                setResult(RESULT_LIST, resultIntent); // Use a custom result code
                finish();
            }
        });
    }
}
