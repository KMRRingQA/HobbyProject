package com.qa.service;

import com.qa.domain.Note;
import com.qa.dto.NoteDTO;
import com.qa.repo.NotesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
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

    @Test
    public void readNotesTest(){
        assertThat(this.service.readNotes())
        .isEqualTo(
                Stream.of(this.mapToDTO(testNoteWithID)).collect(Collectors.toList())
        );
    }

    @Test
    public void createNoteTest(){
        assertEquals(this.mapToDTO(this.testNoteWithID), this.service.createNote(testNote));
    }

    @Test
    public void findNoteByIdTest(){
        assertThat(this.service.findNoteById(this.testNoteWithID.getId())).isEqualTo(this.mapToDTO(this.testNoteWithID));
    }


//    @Test
//    public void updateNoteTest(){
//        Note newNote = new Note("Favourite movies", "The grinch");
//        Note updateNote = new Note(newNote.getTitle(), newNote.getDescription());
//        updateNote.setId(this.testNoteWithID.getId());
//        assertThat(this.service.updateNote(this.testNoteWithID.getId() ,newNote))
//                .isEqualTo(this.mapToDTO(updateNote));
//    }

    @Test
    public void deleteNoteTest(){
        assertThat(this.service.deleteNote(this.testNoteWithID.getId())).isFalse();
    }


}
