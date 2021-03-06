package com.clinicalcenter.com.clinicalsys.model;

import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import static javax.persistence.InheritanceType.JOINED;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "user")
@Inheritance(strategy = JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "adminConfirmed")
    private Boolean adminConfirmed;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "firstName", unique = false, nullable = false)
    private String firstName;

    @Column(name = "lastName", unique = false, nullable = false)
    private String lastName;

    public Boolean getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "firstLogin")
    private Boolean firstLogin;

    @Column(name = "password", unique = false, nullable = false)
    private String password;  /*FIELD PASSWORD???*/

    @Column(name = "address", unique = false)
    private String address;

    @Column(name = "city", unique = false)
    private String city;

    @Column(name = "country", unique = false)
    private String country;

    @Column(name = "phoneNumber", unique = false)
    private String phoneNumber;

    @Column(name = "ssn", unique = false, nullable = false)
    private String ssn;

    @Column(name = "role", unique = false)
    private RoleEnum role;


    public User() {
    }

    public User(User u) {
        this.email = u.getEmail();
        this.adminConfirmed = u.getAdminConfirmed();
        this.active = u.getActive();
        this.firstName = u.getFirstName();
        this.lastName = u.getLastName();
        this.password = u.getPassword();
        this.address = u.getAddress();
        this.city = u.getCity();
        this.country = u.getCountry();
        this.phoneNumber = u.getPhoneNumber();
        this.ssn = u.getSsn();
        this.role = u.getRole();
    }

    public User(String email, String firstName, String lastName, String password, String address, String city, String country, String phoneNumber, String ssn) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.ssn = ssn;
        this.role = RoleEnum.PATIENT;
        this.adminConfirmed = Boolean.FALSE;
        this.active = Boolean.FALSE;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getAdminConfirmed() {
        return adminConfirmed;
    }

    public void setAdminConfirmed(Boolean adminConfirmed) {
        this.adminConfirmed = adminConfirmed;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getEmail().equals(user.getEmail()) &&
                getSsn().equals(user.getSsn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getSsn());
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
