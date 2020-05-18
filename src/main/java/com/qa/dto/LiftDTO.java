package com.qa.dto;

import com.qa.domain.Manufacturer;

import java.math.BigDecimal;
import java.util.Objects;

public class LiftDTO {

    private Long id;
    private String title;
    private String description;
    private Manufacturer manufacturer;

    private Integer carryCapacity;
    private Integer maxSpeed;
    private String dimensions;
    private BigDecimal cost;

    public LiftDTO() {
    }

    public LiftDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LiftDTO liftDTO = (LiftDTO) o;
        return Objects.equals(id, liftDTO.id) &&
                Objects.equals(title, liftDTO.title) &&
                Objects.equals(description, liftDTO.description) &&
                Objects.equals(manufacturer, liftDTO.manufacturer) &&
                Objects.equals(carryCapacity, liftDTO.carryCapacity) &&
                Objects.equals(maxSpeed, liftDTO.maxSpeed) &&
                Objects.equals(dimensions, liftDTO.dimensions) &&
                Objects.equals(cost, liftDTO.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, manufacturer, carryCapacity, maxSpeed, dimensions, cost);
    }

    public Integer getCarryCapacity() {
        return carryCapacity;
    }

    public void setCarryCapacity(Integer carryCapacity) {
        this.carryCapacity = carryCapacity;
    }
}
