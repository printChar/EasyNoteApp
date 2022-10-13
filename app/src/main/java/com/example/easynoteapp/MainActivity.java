package com.example.easynoteapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.easynoteapp.adapters.NoteListAdapter;
import com.example.easynoteapp.database.NoteDAO;
import com.example.easynoteapp.model.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteListAdapter noteListAdapter;
    private RecyclerView recyclerView;
    private ConstraintLayout layout;
    private List<Note> notes = new ArrayList<>();
    private NoteDAO database;
    private ImageView addNoteBtn;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.mainConstraint);
        recyclerView = findViewById(R.id.recycler_home);
        addNoteBtn = findViewById(R.id.add_note_btn);
        textView = new TextView(this);
        database = new NoteDAO();

       if (database.getAll().isEmpty()){
           textView.setText("Add your first note");
           textView.setTextSize(18);
           textView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
           textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
           textView.setPadding(30,500,30,30);
           layout.addView(textView);
       }

        notes.addAll(database.getAll());
        updateRecycle(notes);

        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NoteTakeActivity.class);
                startActivityForResult(intent, 101);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println(Activity.RESULT_OK);
        if(requestCode == 101){
            if(resultCode == Activity.RESULT_OK){
                Note note = (Note) data.getSerializableExtra("note");
                database.addNote(note);
                notes.clear();
                notes.addAll(database.getAll());
                textView.setVisibility(View.GONE);
                noteListAdapter.notifyDataSetChanged();
            }
        }
    }

    private void updateRecycle(List<Note> notes) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
        noteListAdapter = new NoteListAdapter(MainActivity.this, notes, noteClickListener);
        recyclerView.setAdapter(noteListAdapter);
    }

    private final NoteClickListener noteClickListener = new NoteClickListener() {
        @Override
        public void onClick(Note note) {
        }

        @Override
        public void onLongClick(Note note, CardView cardView) {
        }
    };

    private void starUp(){

    }
}