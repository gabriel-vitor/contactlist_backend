package com.api.contactlist.repositories;

import com.api.contactlist.models.ContactListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//JpaRepository brings the simplification of GET,POST,PUT,DELETE methods
//it is necessary to pass the model and identifier
//the @Repository is already used

@Repository
public interface ContactListRepository extends JpaRepository<ContactListModel, UUID> {

    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByName(String name);
    boolean existsByEmail(String email);


}
