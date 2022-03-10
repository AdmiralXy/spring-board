package com.admiralxy.springboard.entity;

import javax.persistence.*;

@Entity
@Table(name = "desks")
public class Desk extends BaseEntity {
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
