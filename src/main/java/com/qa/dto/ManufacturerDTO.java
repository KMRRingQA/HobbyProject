package com.qa.dto;

import com.qa.domain.Lift;
import com.qa.domain.Window;

import java.util.ArrayList;
import java.util.List;

public class ManufacturerDTO {

    private Long id;
    private String name;
    private List<LiftDTO> lifts = new ArrayList<>();
    private List<DoorDTO> doors = new ArrayList<>();
    private List<WindowDTO> windows = new ArrayList<>();

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

    public void setNotes(List<LiftDTO> lifts) {this.lifts = lifts;
    }

    public List<DoorDTO> getDoors() {
        return doors;
    }

    public void setDoors(List<DoorDTO> doors) {
        this.doors = doors;
    }

    public List<WindowDTO> getWindows() {
        return windows;
    }

    public void setWindows(List<WindowDTO> windows) {
        this.windows = windows;
    }

}
