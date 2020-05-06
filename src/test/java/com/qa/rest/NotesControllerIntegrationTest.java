package com.qa.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.domain.Note;
import com.qa.dto.NoteDTO;
import com.qa.repo.NotesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    public void getAllNotesTest() throws Exception {
        List<NoteDTO> noteDTOList = new ArrayList<>();
        noteDTOList.add(noteDTO);
        String content = this.mock.perform(
            request(HttpMethod.GET, "/getAllNotes")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(noteDTOList));
    }

    @Test
    public void getNoteByID() throws Exception {
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getNoteById/" + this.id)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(noteDTO));
    }

    @Test
    public void createNoteTest() throws Exception {
        String result = this.mock.perform(
                request(HttpMethod.POST, "/createNote")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(testNote))
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse()
            .getContentAsString();
        assertEquals(result, this.objectMapper.writeValueAsString(noteDTO));
    }

    @Test
    public void deleteNoteTest() throws Exception {
        this.mock.perform(
                request(HttpMethod.DELETE, "/deleteNote/" + this.id)
        ).andExpect(status().isNoContent());
    }


}
