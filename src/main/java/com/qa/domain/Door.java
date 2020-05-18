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

    private String value1;
    private String value2;
    private String value3;
    private BigDecimal cost;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Manufacturer manufacturer;

    public Door() {
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Door(String title, String description, String value1, String value2, String value3, BigDecimal cost) {
        this.title = title;
        this.description = description;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
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
                Objects.equals(value1, door.value1) &&
                Objects.equals(value2, door.value2) &&
                Objects.equals(value3, door.value3) &&
                Objects.equals(cost, door.cost) &&
                Objects.equals(manufacturer, door.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, value1, value2, value3, cost, manufacturer);
    }
}
