package com.qa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Manufacturer {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.LAZY)
    private List<Lift> lifts = new ArrayList<>();

    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.LAZY)
    private List<Door> doors = new ArrayList<>();

    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.LAZY)
    private List<Window> windows = new ArrayList<>();

    public Manufacturer() {
    }

    public Manufacturer(String name) {
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

    public List<Window> getWindows() {
        return windows;
    }

    public void setWindows(List<Window> notes) {
        this.windows = notes;
    }

    public List<Lift> getLifts() {
        return lifts;
    }

    public void setLifts(List<Lift> notes) {
        this.lifts = notes;
    }

    public List<Door> getDoors() {
        return doors;
    }

    public void setDoors(List<Door> doors) {
        this.doors = doors;
    }
}
