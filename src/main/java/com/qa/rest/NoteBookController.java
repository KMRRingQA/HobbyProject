package com.qa.rest;

import com.qa.domain.Note;
import com.qa.domain.NoteBook;
import com.qa.service.NoteBookService;
import com.qa.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class NoteBookController {

    private final NoteBookService service;

    @Autowired
    public NoteBookController(NoteBookService service) {
        this.service = service;
    }

    @GetMapping("/getAllNoteBooks")
    public List<NoteBook> getAllNotes(){
        return this.service.readNoteBooks();
    }

    @PostMapping("/createNoteBook")
    public NoteBook createNote(@RequestBody NoteBook note){
        return this.service.createNoteBook(note);
    }

    @DeleteMapping("/deleteNoteBook/{id}")
    public boolean deleteNote(@PathVariable Long id){
        return this.service.deleteNoteBook(id);
    }

    @GetMapping("/getNoteBookById/{id}")
    public NoteBook getNoteById(@PathVariable Long id){
        return this.service.findNoteBookById(id);
    }

    @PutMapping("/updateNoteBook/{id}")
    public NoteBook updateNote(@PathVariable Long id, @RequestBody NoteBook note){
        return this.service.updateNoteBook(id, note);
    }

    @PutMapping("/updateNoteBook2")
    public NoteBook updateNote2(@PathParam("id") Long id, @RequestBody NoteBook note){
        return this.service.updateNoteBook(id, note);
    }

}
