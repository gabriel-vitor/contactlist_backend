package com.api.contactlist.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_CONTACT_LIST")
public class ContactListModel implements Serializable {
    private static final long serialVersionUID = 1L;   //Conversion control done by the JVM

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  //automatically generated id
    private UUID id;  //unique identifier, no conflict risk
    @Column(nullable = false, unique = true, length = 10)
    private String name;
    @Column(nullable = false, unique = true, length = 13)
    private String phoneNumber;
    @Column(nullable = false, length = 70)
    private String email;
    @Column(nullable = false)
    private LocalDateTime registrationDate;
    
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    
}
