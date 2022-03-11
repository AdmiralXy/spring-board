package com.admiralxy.springboard.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "task_points",
            joinColumns = {@JoinColumn(name = "task_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "point_id", referencedColumnName = "id")})
    private List<Point> points;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}