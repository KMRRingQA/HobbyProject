package com.qa.service;

import com.qa.domain.Note;
import com.qa.dto.NoteDTO;
import com.qa.repo.NotesRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class NoteServiceIntegrationTest {

    @Autowired
    private NoteService service;

    @Autowired
    private NotesRepository repository;

    @Autowired
    private ModelMapper mapper;

    private Note testNote;

    private Note testNoteWithID;

    private NoteDTO mapToDTO(Note note){
        return this.mapper.map(note, NoteDTO.class);
    }

    @Before
    public void setUp(){
        this.testNote = new Note("My title", "my description");
        this.repository.deleteAll();
        this.testNoteWithID = this.repository.save(this.testNote);
    }



}
