package com.qa.service;

import com.qa.domain.Note;
import com.qa.dto.NoteDTO;
import com.qa.repo.NotesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class NoteServiceUnitTest {

    @InjectMocks
    private NoteService service;

    @Mock
    private NotesRepository repository;

    @Mock
    private ModelMapper mapper;

    private List<Note> noteList;

    private Note testNote;

    private long id = 1L;

    private Note testNoteWithID;

    private NoteDTO noteDTO;

    private NoteDTO mapToDTO(Note note){
        return this.mapper.map(note, NoteDTO.class);
    }

    @Before
    public void setUp(){
        this.noteList = new ArrayList<>();
        this.testNote = new Note("Shopping list", "Beer and even more beer");
        this.noteList.add(testNote);
        this.testNoteWithID = new Note(testNote.getTitle(), testNote.getDescription());
        this.testNoteWithID.setId(id);
        this.noteDTO = this.mapToDTO(testNoteWithID);
    }

    @Test
    public void getAllNotesTest(){
        when(repository.findAll()).thenReturn(this.noteList);
        when(this.mapper.map(testNoteWithID, NoteDTO.class)).thenReturn(noteDTO);
        assertFalse("Service returned no Notes", this.service.readNotes().isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void createNoteTest(){
        when(repository.save(testNote)).thenReturn(testNoteWithID);
        when(this.mapper.map(testNoteWithID, NoteDTO.class)).thenReturn(noteDTO);
        assertEquals(this.service.createNote(testNote), this.noteDTO);
        verify(repository, times(1)).save(this.testNote);
    }

    @Test
    public void findNoteByIdTest(){
        when(this.repository.findById(id)).thenReturn(java.util.Optional.ofNullable(testNoteWithID));
        when(this.mapper.map(testNoteWithID, NoteDTO.class)).thenReturn(noteDTO);
        assertEquals(this.service.findNoteById(this.id), noteDTO);
        verify(repository, times(1)).findById(id);
    }



}
