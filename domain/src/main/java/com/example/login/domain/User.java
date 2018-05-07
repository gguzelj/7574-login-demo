package com.example.login.domain;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import javax.persistence.*;
import java.util.Objects;

import static com.google.common.base.Charsets.UTF_8;
import static java.util.Objects.isNull;

@Entity
public class User {

    private static final HashFunction HASH_FUNCTION = Hashing.sha1();

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;

    private User() {
    }

    public User(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = HASH_FUNCTION.hashString(password, UTF_8).toString();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean isSamePassword(String toCompare) {
        return HASH_FUNCTION.hashString(toCompare, UTF_8).toString().equals(this.password);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(email, password);
    }
}
