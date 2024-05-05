package com.haratres.ecommerce.domain;


import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id",referencedColumnName = "id")
    private Cart cart;




    public User() {
    }

    public User(Long id, String email, String password, String name, String surname, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.role = Role.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }


    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email= email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public static final class UserBuilder {
        private Long id;
        private String email;
        private String password;
        private String name;
        private String surname;
        private String phoneNumber;
        private Role role;

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public UserBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserBuilder withRole(Role role) {
            this.role = role;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setEmail(email);
            user.setPassword(password);
            user.setName(name);
            user.setSurname(surname);
            user.setPhoneNumber(phoneNumber);
            user.setRole(role);
            return user;
        }
    }
}
