package com.qa.service;

import com.qa.domain.Note;
import com.qa.domain.NoteBook;
import com.qa.dto.NoteBookDTO;
import com.qa.exceptions.NoteBookNotFoundException;
import com.qa.repo.NoteBookRepository;
import com.qa.repo.NotesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteBookService {

    private final NoteBookRepository noteBookRepository;

    private final NotesRepository notesRepository;

    private final ModelMapper mapper;

    @Autowired
    public NoteBookService(NoteBookRepository noteBookRepository, NotesRepository notesRepository, ModelMapper mapper) {
        this.noteBookRepository = noteBookRepository;
        this.notesRepository = notesRepository;
        this.mapper = mapper;
    }

    private NoteBookDTO mapToDTO(NoteBook noteBook){
        return this.mapper.map(noteBook, NoteBookDTO.class);
    }

    public List<NoteBookDTO> readNoteBooks(){
        return this.noteBookRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public NoteBookDTO createNoteBook(NoteBook noteBook){
        return this.mapToDTO(this.noteBookRepository.save(noteBook));
    }

    public NoteBookDTO findNoteBookById(Long id){
        return this.mapToDTO(this.noteBookRepository.findById(id).orElseThrow(NoteBookNotFoundException::new));
    }

    public NoteBookDTO updateNoteBook(Long id, NoteBook noteBook){
        NoteBook update = this.noteBookRepository.findById(id).orElseThrow(NoteBookNotFoundException::new);
        update.setName(noteBook.getName());
        NoteBook tempNoteBook = this.noteBookRepository.save(update);
        return this.mapToDTO(tempNoteBook);
    }

    public boolean deleteNoteBook(Long id){
        if(!this.noteBookRepository.existsById(id)){
            throw new NoteBookNotFoundException();
        }
        this.noteBookRepository.deleteById(id);
        return this.noteBookRepository.existsById(id);
    }

    public NoteBookDTO addNoteToNoteBook(Long id, Note note){
        NoteBook noteBook = this.noteBookRepository.findById(id).orElseThrow(NoteBookNotFoundException::new);
        Note tmp = this.notesRepository.saveAndFlush(note);
        noteBook.getNotes().add(tmp);
        return this.mapToDTO(this.noteBookRepository.saveAndFlush(noteBook));
    }

}
