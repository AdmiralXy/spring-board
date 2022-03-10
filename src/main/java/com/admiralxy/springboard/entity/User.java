package com.admiralxy.springboard.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_desks",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "desk_id", referencedColumnName = "id")})
    private List<Desk> desks;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Desk> getDesks() {
        return desks;
    }

    public void setDesks(List<Desk> desks) {
        this.desks = desks;
    }
}
