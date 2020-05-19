package com.qa.rest;

import com.qa.domain.Window;
import com.qa.dto.WindowDTO;
import com.qa.service.WindowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WindowController {

    private final WindowService service;

    @Autowired
    public WindowController(WindowService service) {
        this.service = service;
    }

    @GetMapping("/getAllWindows")
    public ResponseEntity<List<WindowDTO>> getAllWindows(){
        return ResponseEntity.ok(this.service.readWindows());
    }

    @PostMapping("/createWindow")
    public ResponseEntity<WindowDTO> createWindow(@RequestBody Window window){
        return new ResponseEntity<WindowDTO>(this.service.createWindow(window), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteWindow/{id}")
    public ResponseEntity<?> deleteWindow(@PathVariable Long id){
        return this.service.deleteWindow(id)
            ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
            : ResponseEntity.noContent().build();
    }

    @GetMapping("/getWindowById/{id}")
    public ResponseEntity<WindowDTO> getWindowById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findWindowById(id));
    }

    @PutMapping("/updateWindow/{id}")
    public ResponseEntity<WindowDTO> updateWindow(@PathVariable Long id, @RequestBody Window window){
        return ResponseEntity.ok(this.service.updateWindow(id, window));
    }

}
