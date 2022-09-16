package com.api.contactlist.services;

import com.api.contactlist.models.ContactListModel;
import com.api.contactlist.repositories.ContactListRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContactListService {


    //final - your value will be assigned only once
    final ContactListRepository contactListRepository;

    public ContactListService(ContactListRepository contactListRepository) {
        this.contactListRepository = contactListRepository;
    }

    @Transactional //if there are errors, it will return to normal
    public ContactListModel save(ContactListModel contactListModel) {
        return contactListRepository.save(contactListModel);
    }

    public boolean existsByPhoneNumber(String phoneNumber) {
        return contactListRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean existsByName(String name) {
        return contactListRepository.existsByName(name);
    }

    public boolean existsByEmail(String email) {
        return contactListRepository.existsByEmail(email);
    }

    public Page<ContactListModel> findAll(Pageable pageable) {
        return contactListRepository.findAll(pageable);
    }

    public Optional<ContactListModel> findById(UUID id) {
        return contactListRepository.findById(id);
    }

    @Transactional  //if something is wrong... rollback
    public void delete(ContactListModel contactListModel) {
        contactListRepository.delete(contactListModel);
    }
}
