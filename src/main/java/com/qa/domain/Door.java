package com.qa.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Door {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String title;
    private String description;

    private String bwf;
    private String thermalResistance;
    private String dimensions;
    private BigDecimal cost;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Manufacturer manufacturer;

    public Door() {
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

    public Door(String title, String description, String bwf, String thermalResistance, String dimensions, BigDecimal cost) {
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

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Door door = (Door) o;
        return Objects.equals(id, door.id) &&
                Objects.equals(title, door.title) &&
                Objects.equals(description, door.description) &&
                Objects.equals(bwf, door.bwf) &&
                Objects.equals(thermalResistance, door.thermalResistance) &&
                Objects.equals(dimensions, door.dimensions) &&
                Objects.equals(cost, door.cost) &&
                Objects.equals(manufacturer, door.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, bwf, thermalResistance, dimensions, cost, manufacturer);
    }
}
