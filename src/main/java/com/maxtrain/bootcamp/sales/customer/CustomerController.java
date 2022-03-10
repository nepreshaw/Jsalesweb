package com.maxtrain.bootcamp.sales.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
//send and receive json data, not instances of Customer
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
	//Iterable will be a collection, like IEnumerable. Only use if you have more than one
	public ResponseEntity<Iterable<Customer>> getCustomers(){
		var customers = custRepo.findAll();
		return new ResponseEntity<Iterable<Customer>>(customers, HttpStatus.OK);
	}
	
	//ResponseEntity is just like actionresult in c#
	//
	@GetMapping("{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable int id){
		var customer = custRepo.findById(id);
		if(customer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer) {
		if(customer == null || customer.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var cust = custRepo.save(customer);
		return new ResponseEntity<Customer>(cust, HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity putCustomer(@PathVariable int id, @RequestBody Customer customer) {
		if(customer == null || customer.getId() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var cust = custRepo.findById(customer.getId());
		if(cust.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		custRepo.save(customer);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteCustomer(@PathVariable int id) {
		var customer = custRepo.findById(id);
		if(customer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		custRepo.delete(customer.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
















