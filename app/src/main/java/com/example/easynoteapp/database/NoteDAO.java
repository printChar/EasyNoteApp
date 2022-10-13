package com.example.easynoteapp.database;

import com.example.easynoteapp.model.Note;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NoteDAO implements Serializable {

    private List<Note> noteList = new ArrayList<>();

    public List<Note> getAll(){
        return noteList;
    }

    public void addNote(Note note){
        noteList.add(note);
        System.out.println("Added a note.");
    }

    public void updateNote(int id, Note newNote){
        Note note = findNote(id);
        noteList.remove(note);
        noteList.add(newNote);
        System.out.println("Updated a note.");
    }

    public void deleteNote(int id){
        Note note = findNote(id);
        noteList.remove(note);
        System.out.println("Deleted a note.");
    }

    public Note findNote(int id){
        Note note;
        List<Note> newList;
        newList = getAll();
        note = newList.get(id);
        return note;
    }


}
