package com.qa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class NoteBook {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "noteBook", fetch = FetchType.LAZY)
    private List<Note> notes = new ArrayList<>();

    public NoteBook() {
    }

    public NoteBook(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
