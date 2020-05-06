package com.qa.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.domain.Note;
import com.qa.dto.NoteDTO;
import com.qa.repo.NotesRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NotesControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private NotesRepository repository;

    @Autowired
    private ModelMapper mapper;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Note testNote;

    private Note testNoteWithID;

    private long id;

    private NoteDTO noteDTO;

    private NoteDTO mapToDTO(Note note){
        return this.mapper.map(note, NoteDTO.class);
    }

    @Before
    public void setUp(){
        this.repository.deleteAll();
        this.testNote = new Note("test note", "test description");
        this.testNoteWithID = this.repository.save(testNote);
        this.id = testNoteWithID.getId();
        this.noteDTO = this.mapToDTO(testNoteWithID);
    }

}
