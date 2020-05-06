package com.qa.service;

import com.qa.domain.Note;
import com.qa.dto.NoteDTO;
import com.qa.repo.NotesRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

}
