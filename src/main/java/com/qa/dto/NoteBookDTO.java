package com.qa.dto;

import com.qa.domain.Note;
import java.util.ArrayList;
import java.util.List;

public class NoteBookDTO {

    private Long id;
    private String name;
    private List<NoteDTO> notes;

    public NoteBookDTO() {
    }

    public NoteBookDTO(String name, List<NoteDTO> notes) {
        super();
        this.name = name;
        this.notes = notes;
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

    public List<NoteDTO> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteDTO> notes) {
        this.notes = notes;
    }

}
