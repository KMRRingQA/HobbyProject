package com.qa.dto;

import com.qa.domain.Manufacturer;

import java.math.BigDecimal;
import java.util.Objects;

public class DoorDTO {

    private Long id;
    private String title;
    private String description;
    private Manufacturer manufacturer;
    private String bwf;
    private String thermalResistance;
    private String dimensions;
    private BigDecimal cost;
    public DoorDTO() {
    }

    public DoorDTO(String title, String description, String bwf, String thermalResistance, String dimensions, BigDecimal cost) {
        this.title = title;
        this.description = description;
        this.bwf = bwf;
        this.thermalResistance = thermalResistance;
        this.dimensions = dimensions;
        this.cost = cost;
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
        DoorDTO doorDTO = (DoorDTO) o;
        return Objects.equals(id, doorDTO.id) &&
                Objects.equals(title, doorDTO.title) &&
                Objects.equals(description, doorDTO.description) &&
                Objects.equals(manufacturer, doorDTO.manufacturer) &&
                Objects.equals(bwf, doorDTO.bwf) &&
                Objects.equals(thermalResistance, doorDTO.thermalResistance) &&
                Objects.equals(dimensions, doorDTO.dimensions) &&
                Objects.equals(cost, doorDTO.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, manufacturer, bwf, thermalResistance, dimensions, cost);
    }

    public String getBwf() {
        return bwf;
    }

    public void setBwf(String bwf) {
        this.bwf = bwf;
    }

    public String getThermalResistance() {
        return thermalResistance;
    }

    public void setThermalResistance(String thermalResistance) {
        this.thermalResistance = thermalResistance;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
