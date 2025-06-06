package com.example.User_Note.model;

import jakarta.persistence.*;


@Entity
@Table(name = "user_note")
public class UserNotes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 4000)
    private String note;
    private String created;
    private String last_edited;

    public UserNotes() {}
    public UserNotes(int id, String note, String last_edited, String created) {
        this.id = id;
        this.note = note;
        this.last_edited = last_edited;
        this.created = created;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLast_edited() {
        return last_edited;
    }

    public void setLast_edited(String last_edited) {
        this.last_edited = last_edited;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created)
    {
        this.created = created;
    }

}
