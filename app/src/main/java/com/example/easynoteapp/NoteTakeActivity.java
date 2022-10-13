package com.example.easynoteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.easynoteapp.model.Note;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteTakeActivity extends AppCompatActivity {

    private EditText editText_title, editText_notes;
    private ImageView imageView_save;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_take);

        imageView_save = findViewById(R.id.imageView_save);
        editText_title = findViewById(R.id.editText_title);
        editText_notes = findViewById(R.id.editText_notes);

        imageView_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editText_title.getText().toString();
                String desc = editText_notes.getText().toString();

                if(desc.isEmpty()){
                    Toast.makeText(NoteTakeActivity.this, "Add text.", Toast.LENGTH_LONG).show();
                    return;
                }

                SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm a");
                Date date = new Date();

                note = new Note();
                note.setTitle(title);
                note.setDesc(desc);
                note.setDate(formatter.format(date));

                Intent intent = new Intent();
                intent.putExtra("note", note);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }
}