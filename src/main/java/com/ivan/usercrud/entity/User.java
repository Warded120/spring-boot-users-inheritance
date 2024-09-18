package com.ivan.usercrud.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")  )
    private Collection<Role> roles;

    public User(String username, String password, boolean enabled, String topRole) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = getRolesFromTemplate(topRole);
    }

    private List<Role> getRolesFromTemplate(String topRole) {
        List<Role> tempAuthorities = new ArrayList<>();

        switch (topRole) {
            case "customer":
                tempAuthorities.add(new Role("ROLE_CUSTOMER"));
                break;
            case "manager":
                tempAuthorities.add(new Role("ROLE_CUSTOMER"));
                tempAuthorities.add(new Role("ROLE_MANAGER"));
                break;
            case "admin":
                tempAuthorities.add(new Role("ROLE_CUSTOMER"));
                tempAuthorities.add(new Role("ROLE_MANAGER"));
                tempAuthorities.add(new Role("ROLE_ADMIN"));
                break;
        }
        return tempAuthorities;
    }
}
