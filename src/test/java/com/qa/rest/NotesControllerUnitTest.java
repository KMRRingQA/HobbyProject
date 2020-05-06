package com.qa.rest;

import com.qa.domain.Note;
import com.qa.dto.NoteDTO;
import com.qa.service.NoteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NotesControllerUnitTest {

    @InjectMocks
    private NoteController notesController;

    @Mock
    private NoteService service;

    private List<Note> notes;

    private Note testNote;

    private Note testNoteWitId;

    private long id = 1L;

    private NoteDTO noteDTO;

    private final ModelMapper mapper = new ModelMapper();

    private NoteDTO mapToDTO(Note note){
        return this.mapper.map(note, NoteDTO.class);
    }

    @Before
    public void setUp(){
        this.notes = new ArrayList<>();
        this.testNote = new Note("Test title", "Test description");
        this.notes.add(testNote);
        this.testNoteWitId = new Note(testNote.getTitle(), testNote.getDescription());
        this.testNoteWitId.setId(this.id);
        this.noteDTO = this.mapToDTO(testNoteWitId);
    }

    @Test
    public void getAllNotesTest(){
        when(service.readNotes()).thenReturn(this.notes.stream().map(this::mapToDTO).collect(Collectors.toList()));
        assertFalse("No notes found", this.notesController.getAllNotes().getBody().isEmpty());
        verify(service, times(1)).readNotes();
    }



}
