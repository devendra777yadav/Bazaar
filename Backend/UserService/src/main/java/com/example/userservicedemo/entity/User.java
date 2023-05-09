package com.example.userservicedemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @NotEmpty(message = "name is required")
    @Size(min = 5, message = "name must be at least 5 characters")
    @Column
    private String name;

    @NotEmpty(message = "username is required")
    @Size(min = 5, message = "username must be at least 5 characters and unique")
    @Column(name="username",nullable = false)
    private String username;

    @NotEmpty(message = "The email address is required.")
    @Email(message = "The email address is invalid.")
    @Column(name="email",nullable = false)
    private String email;

    @NotEmpty(message = "password is required.")
    @Size(min = 5, message = "password must be at least 5 characters and not exceed 10 characters")
    @Column(name="password",nullable = false)
    private String password;

    @OneToMany(targetEntity = UserAddress.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private List<UserAddress> userAddressList;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "roleId")
    )
    private Set<Role> roles;
}
