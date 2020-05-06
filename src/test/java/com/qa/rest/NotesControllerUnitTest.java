package com.qa.rest;

import com.qa.domain.Note;
import com.qa.dto.NoteDTO;
import com.qa.service.NoteService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.List;

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





}
