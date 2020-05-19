package com.qa.rest;

import com.qa.domain.Door;
import com.qa.dto.DoorDTO;
import com.qa.service.DoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoorController {

    private final DoorService service;

    @Autowired
    public DoorController(DoorService service) {
        this.service = service;
    }

    @GetMapping("/getAllDoors")
    public ResponseEntity<List<DoorDTO>> getAllDoors(){
        return ResponseEntity.ok(this.service.readDoors());
    }

    @PostMapping("/createDoor")
    public ResponseEntity<DoorDTO> createDoor(@RequestBody Door door){
        return new ResponseEntity<DoorDTO>(this.service.createDoor(door), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteDoor/{id}")
    public ResponseEntity<?> deleteDoor(@PathVariable Long id){
        return this.service.deleteDoor(id)
            ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
            : ResponseEntity.noContent().build();
    }

    @GetMapping("/getDoorById/{id}")
    public ResponseEntity<DoorDTO> getDoorById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findDoorById(id));
    }

    @PutMapping("/updateDoor/{id}")
    public ResponseEntity<DoorDTO> updateDoor(@PathVariable Long id, @RequestBody Door door){
        return ResponseEntity.ok(this.service.updateDoor(id, door));
    }

}
