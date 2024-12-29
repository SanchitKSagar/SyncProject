package com.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	long id;
	
	public ResourceNotFoundException(long id) {
		super("No record found with ID : " + id);
		this.id = id;
	}
	

}
