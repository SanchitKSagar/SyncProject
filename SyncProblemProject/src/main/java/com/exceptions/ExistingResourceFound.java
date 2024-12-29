package com.exceptions;

public class ExistingResourceFound extends Exception{

	long id;
	public ExistingResourceFound(long id) {
		super("Existing record found with ID : " + id);
		this.id = id;
	}
}
