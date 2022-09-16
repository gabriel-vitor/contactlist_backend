package com.api.contactlist.controllers;

import com.api.contactlist.dtos.ContactListDto;
import com.api.contactlist.models.ContactListModel;
import com.api.contactlist.repositories.ContactListRepository;
import com.api.contactlist.services.ContactListService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600) //allows access from any source
@RequestMapping("/contact-list")
public class ContactListController {

    //create injection point
    final ContactListService contactListService;

    //constructor
    public ContactListController(ContactListService contactListService) {
        this.contactListService = contactListService;
    }

    @PostMapping
    public ResponseEntity<Object> saveContactList(@RequestBody @Valid ContactListDto contactListDto) {

        //It is necessary to check if some information is in the database

        if(contactListService.existsByPhoneNumber(contactListDto.getPhoneNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: phone number is already in use!");
        }
        
        var contactListModel = new ContactListModel();
        BeanUtils.copyProperties(contactListDto, contactListModel);
        contactListModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(contactListService.save(contactListModel));

    }

    @GetMapping
    public ResponseEntity<Page<ContactListModel>> getAllContactLists(@PageableDefault(page=0, size = 10, sort="id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(contactListService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneContactList(@PathVariable(value = "id") UUID id){
        Optional<ContactListModel> contactListModelOptional = contactListService.findById(id);
        if (!contactListModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(contactListModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteContactList(@PathVariable(value = "id") UUID id){
        Optional<ContactListModel> contactListModelOptional = contactListService.findById(id);
        if (!contactListModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found.");
        }
        contactListService.delete(contactListModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Contact deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateContactList(@PathVariable(value = "id") UUID id, @RequestBody @Valid ContactListDto contactListDto){
        //check if resource exists in database
        Optional<ContactListModel> contactListModelOptional = contactListService.findById(id);
        if (!contactListModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found.");
        }

        //need to convert DTO to models to put in database
        //This way will keep the same ID
        var contactListModel = new ContactListModel();
        BeanUtils.copyProperties(contactListDto, contactListModel);
        contactListModel.setId(contactListModelOptional.get().getId());
        contactListModel.setRegistrationDate(contactListModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(contactListService.save(contactListModel));

    }
}

