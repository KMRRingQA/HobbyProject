package com.qa.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Lift {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;

    private Integer carryCapacity;
    private Integer maxSpeed;
    private String dimensions;
    private BigDecimal cost;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//  @JoinColumn(name = "lift_book_id")
    private Manufacturer manufacturer;

    public Lift() {
    }

    public Lift(String title, String description) {
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
        Lift lift = (Lift) o;
        return Objects.equals(id, lift.id) &&
                Objects.equals(title, lift.title) &&
                Objects.equals(description, lift.description) &&
                Objects.equals(carryCapacity, lift.carryCapacity) &&
                Objects.equals(maxSpeed, lift.maxSpeed) &&
                Objects.equals(dimensions, lift.dimensions) &&
                Objects.equals(cost, lift.cost) &&
                Objects.equals(manufacturer, lift.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, carryCapacity, maxSpeed, dimensions, cost, manufacturer);
    }

    public Integer getCarryCapacity() {
        return carryCapacity;
    }

    public void setCarryCapacity(Integer carryCapacity) {
        this.carryCapacity = carryCapacity;
    }
}
