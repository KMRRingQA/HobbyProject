package com.qa.rest;

import com.qa.domain.Note;
import com.qa.domain.NoteBook;
import com.qa.dto.NoteBookDTO;
import com.qa.service.NoteBookService;
import com.qa.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<NoteBookDTO>> getAllNotes(){
        return new ResponseEntity<List<NoteBookDTO>>(this.service.readNoteBooks(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/createNoteBook")
    public ResponseEntity<NoteBookDTO> createNote(@RequestBody NoteBook note){
        return new ResponseEntity<NoteBookDTO>(this.service.createNoteBook(note), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteNoteBook/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id){
        return this.service.deleteNoteBook(id)
            ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
            : ResponseEntity.noContent().build();
    }

    @GetMapping("/getNoteBookById/{id}")
    public ResponseEntity<NoteBookDTO> getNoteById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findNoteBookById(id));
    }

    @PutMapping("/updateNoteBook/{id}")
    public ResponseEntity<NoteBookDTO> updateNote(@PathVariable Long id, @RequestBody NoteBook note){
        return ResponseEntity.ok(this.service.updateNoteBook(id, note));
    }

    @PutMapping("/updateNoteBook2")
    public ResponseEntity<NoteBookDTO> updateNote2(@PathParam("id") Long id, @RequestBody NoteBook note){
        return ResponseEntity.ok(this.service.updateNoteBook(id, note));
    }

}
