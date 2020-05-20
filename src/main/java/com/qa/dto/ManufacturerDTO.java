package com.qa.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManufacturerDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private List<LiftDTO> lifts = new ArrayList<>();
    private List<DoorDTO> doors = new ArrayList<>();
    private List<WindowDTO> windows = new ArrayList<>();

    public ManufacturerDTO() {
    }

    public ManufacturerDTO(String name,String email,String password) {
        super();
        this.email = email;
        this.password = password;
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

    public List<LiftDTO> getLifts() {
        return lifts;
    }

    public void setLifts(List<LiftDTO> lifts) {this.lifts = lifts;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManufacturerDTO that = (ManufacturerDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, lifts, doors, windows);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "ManufacturerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", lifts=" + lifts +
                ", doors=" + doors +
                ", windows=" + windows +
                '}';
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
