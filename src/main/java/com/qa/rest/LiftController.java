package com.qa.rest;

import com.qa.domain.Lift;
import com.qa.dto.LiftDTO;
import com.qa.service.LiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class LiftController {

    private final LiftService service;

    @Autowired
    public LiftController(LiftService service) {
        this.service = service;
    }

    @GetMapping("/getAllLifts")
    public ResponseEntity<List<LiftDTO>> getAllLifts(){
        return ResponseEntity.ok(this.service.readLifts());
    }

    @PostMapping("/createLift")
    public ResponseEntity<LiftDTO> createLift(@RequestBody Lift lift){
        return new ResponseEntity<LiftDTO>(this.service.createLift(lift), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteLift/{id}")
    public ResponseEntity<?> deleteLift(@PathVariable Long id){
        return this.service.deleteLift(id)
            ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
            : ResponseEntity.noContent().build();
    }

    @GetMapping("/getLiftById/{id}")
    public ResponseEntity<LiftDTO> getLiftById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findLiftById(id));
    }

    @PutMapping("/updateLift/{id}")
    public ResponseEntity<LiftDTO> updateLift(@PathVariable Long id, @RequestBody Lift lift){
        return ResponseEntity.ok(this.service.updateLift(id, lift));
    }
//
//    @PutMapping("/updateLift2")
//    public ResponseEntity<LiftDTO> updateLift2(@PathParam("id") Long id, @RequestBody Lift lift){
//        return ResponseEntity.ok(this.service.updateLift(id, lift));
//    }

}
