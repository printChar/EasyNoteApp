package com.example.easynoteapp.model;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable {

    private int id;
    private String title;
    private String desc;
    private String date;

    public Note(int id, String title, String desc, String date) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.date = date;
    }

    public Note() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
      /*  Date todaysDate = new Date();
        if (date.isEmpty()){
            this.date = todaysDate.toString();
        }*/
        this.date = date;
    }
}
