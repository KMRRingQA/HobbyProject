package com.qa.rest;

import com.qa.domain.Note;
import com.qa.domain.Manufacturer;
import com.qa.dto.ManufacturerDTO;
import com.qa.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class ManufacturerController {

    private final ManufacturerService service;

    @Autowired
    public ManufacturerController(ManufacturerService service) {
        this.service = service;
    }

    @GetMapping("/getAllManufacturers")
    public ResponseEntity<List<ManufacturerDTO>> getAllNotes(){
        return ResponseEntity.ok(this.service.readManufacturers());
    }

    @PostMapping("/createManufacturer")
    public ResponseEntity<ManufacturerDTO> createNote(@RequestBody Manufacturer note){
        return new ResponseEntity<ManufacturerDTO>(this.service.createManufacturer(note), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteManufacturer/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id){
        return this.service.deleteManufacturer(id)
            ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
            : ResponseEntity.noContent().build();
    }

    @GetMapping("/getManufacturerById/{id}")
    public ResponseEntity<ManufacturerDTO> getNoteById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findManufacturerById(id));
    }

    @PutMapping("/updateManufacturer/{id}")
    public ResponseEntity<ManufacturerDTO> updateNote(@PathVariable Long id, @RequestBody Manufacturer note){
        return ResponseEntity.ok(this.service.updateManufacturer(id, note));
    }

    @PutMapping("/updateManufacturer2")
    public ResponseEntity<ManufacturerDTO> updateNote2(@PathParam("id") Long id, @RequestBody Manufacturer note){
        return ResponseEntity.ok(this.service.updateManufacturer(id, note));
    }

}
