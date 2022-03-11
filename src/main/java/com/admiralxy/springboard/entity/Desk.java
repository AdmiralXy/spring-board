package com.admiralxy.springboard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "desks")
public class Desk extends BaseEntity {
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_desks",
            joinColumns = @JoinColumn(name = "desk_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private List<User> users;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "desk_tasks",
            joinColumns = {@JoinColumn(name = "desk_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "task_id", referencedColumnName = "id")})
    private List<Task> tasks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        if (users == null)
            users = new ArrayList<>();
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Desk)) return false;
        Desk desk = (Desk) o;
        return Objects.equals(getId(), desk.getId()) && Objects.equals(getName(), desk.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
