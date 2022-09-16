package com.api.contactlist.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ContactListDto {

    @NotBlank
    private String name;
    @NotBlank
    @Size(max = 13)
    private String phoneNumber;
    @NotBlank
    private String email;
    
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
    

    
}
