package com.qa.service;

import com.qa.domain.Note;
import com.qa.domain.NoteBook;
import com.qa.exceptions.NoteBookNotFoundException;
import com.qa.exceptions.NoteNotFoundException;
import com.qa.repo.NoteBookRepository;
import com.qa.repo.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteBookService {

    private final NoteBookRepository repo;

    @Autowired
    public NoteBookService(NoteBookRepository repo) {
        this.repo = repo;
    }

    public List<NoteBook> readNoteBooks(){
        return this.repo.findAll();
    }

    public NoteBook createNoteBook(NoteBook noteBook){
        return this.repo.save(noteBook);
    }

    public NoteBook findNoteBookById(Long id){
        return this.repo.findById(id).orElseThrow(NoteBookNotFoundException::new);
    }

    public NoteBook updateNoteBook(Long id, NoteBook noteBook){
        NoteBook update = findNoteBookById(id);
        update.setName(noteBook.getName());
        return this.repo.save(update);
    }

    public boolean deleteNoteBook(Long id){
        if(!this.repo.existsById(id)){
            throw new NoteBookNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }

}
