package com.example.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TextInput {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long textId;

    private String username;

    @Column(length = 1000) // Assumes max length of text is 1000 chars
    private String text;

    private long time;

    public TextInput(String text, long time, long textId, String username) {
        this.text = text;
        this.time = time;
        this.textId = textId;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
