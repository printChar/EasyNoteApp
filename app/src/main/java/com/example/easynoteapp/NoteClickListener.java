package com.example.easynoteapp;

import androidx.cardview.widget.CardView;

import com.example.easynoteapp.model.Note;

public interface NoteClickListener {

    void onClick(Note note);
    void onLongClick(Note note, CardView cardView);


}
