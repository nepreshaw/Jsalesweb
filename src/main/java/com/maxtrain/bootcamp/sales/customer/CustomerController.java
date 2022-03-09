package com.maxtrain.bootcamp.sales.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
//send and receive json data
@RestController
//makes url for controllers
@RequestMapping("/api/customers")

public class CustomerController {
	//take the instance of the repository and create a repo
	//autowired loads in the instance of the repository without having to write more code
	//otherwise we would have to set our custRepo into a new instance
	@Autowired
	//this is the equivalent of _context
	private CustomerRepository custRepo;
	
	@GetMapping 
	//this will bring back an array of customer instances
	//enumeration is when you define a type and only allow a subset of values
	public ResponseEntity<Iterable<Customer>> GetCustomers(){
		var customers = custRepo.findAll();
		return new ResponseEntity<Iterable<Customer>>(customers, HttpStatus.OK);
	}
}
