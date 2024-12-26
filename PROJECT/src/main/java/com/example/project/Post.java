package com.example.project;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Post
{
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty date;
    private final SimpleStringProperty post;
    private final SimpleStringProperty photo;
    private final SimpleIntegerProperty mood;

    public Post(int id, String date, String post, String photo, int mood)
    {
        this.id = new SimpleIntegerProperty(id);
        this.date = new SimpleStringProperty(date);
        this.post = new SimpleStringProperty(post);
        this.photo = new SimpleStringProperty(photo);
        this.mood = new SimpleIntegerProperty(mood);
    }

    public int getId() {return id.get();}
    public void setId(int val_id) {id.set(val_id);}

    public String getDate() { return date.get();}
    public void setDate(String val_date) {
        date.set(val_date);
    }

    public String getPost() {
        return post.get();
    }
    public void setPost(String val_post) {
        post.set(val_post);
    }

    public String getPhoto() { return photo.get();}
    public void setPhoto(String val_photo) {
        photo.set(val_photo);
    }

    public double getMood() {
        return mood.get();
    }
    public void setMood(int val_mood) {
        mood.set(val_mood);
    }

}
