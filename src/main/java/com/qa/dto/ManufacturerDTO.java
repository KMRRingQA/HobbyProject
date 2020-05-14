package com.qa.dto;

import java.util.ArrayList;
import java.util.List;

public class ManufacturerDTO {

    private Long id;
    private String name;
    private List<LiftDTO> lifts = new ArrayList<>();
    private List<DoorDTO> doors = new ArrayList<>();

    public ManufacturerDTO() {
    }

    public ManufacturerDTO(String name) {
        super();
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LiftDTO> getNotes() {
        return lifts;
    }

    public void setNotes(List<LiftDTO> notes) {
        this.lifts = notes;
    }

    public List<DoorDTO> getDoors() {
        return doors;
    }

    public void setDoors(List<DoorDTO> doors) {
        this.doors = doors;
    }

}
