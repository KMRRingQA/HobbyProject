package com.qa.rest;

import com.qa.domain.Note;
import com.qa.dto.NoteDTO;
import com.qa.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class NoteController {

    private final NoteService service;

    @Autowired
    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping("/getAllNotes")
    public ResponseEntity<List<NoteDTO>> getAllNotes(){
        return ResponseEntity.ok(this.service.readNotes());
    }

    @PostMapping("/createNote")
    public ResponseEntity<NoteDTO> createNote(@RequestBody Note note){
        return new ResponseEntity<NoteDTO>(this.service.createNote(note), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteNote/{id}")
    public boolean deleteNote(@PathVariable Long id){
        return this.service.deleteNote(id);
    }

    @GetMapping("/getNoteById/{id}")
    public Note getNoteById(@PathVariable Long id){
        return this.service.findNoteById(id);
    }

    @PutMapping("/updateNote/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody Note note){
        return this.service.updateNote(id, note);
    }

    @PutMapping("/updateNote2")
    public Note updateNote2(@PathParam("id") Long id, @RequestBody Note note){
        return this.service.updateNote(id, note);
    }

}
