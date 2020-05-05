package com.qa.service;

import com.qa.domain.Note;
import com.qa.dto.NoteDTO;
import com.qa.exceptions.NoteNotFoundException;
import com.qa.repo.NotesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NotesRepository repo;

    private final ModelMapper mapper;

    @Autowired
    public NoteService(NotesRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private NoteDTO mapToDTO(Note note){
        return this.mapper.map(note, NoteDTO.class);
    }


    public List<NoteDTO> readNotes(){
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public NoteDTO createNote(Note note){
        Note tempNote = this.repo.save(note);
        return this.mapToDTO(tempNote);
    }

    public NoteDTO findNoteById(Long id){
        return this.mapToDTO(this.repo.findById(id).orElseThrow(NoteNotFoundException::new));
    }

    public NoteDTO updateNote(Long id, Note note){
        Note update = this.repo.findById(id).orElseThrow(NoteNotFoundException::new);
        update.setTitle(note.getTitle());
        update.setDescription(note.getDescription());
        Note tempNote = this.repo.save(note);
        return this.mapToDTO(tempNote);
    }

    public boolean deleteNote(Long id){
        if(!this.repo.existsById(id)){
            throw new NoteNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }


}
