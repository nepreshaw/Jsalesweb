package com.maxtrain.bootcamp.sales.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrdersController {
	@Autowired
	private OrdersRepository ordRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Orders>> getOrders(){
		var order = ordRepo.findAll();
		return new ResponseEntity<Iterable<Orders>>(order, HttpStatus.OK);
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<Orders> getOrder(@PathVariable int id){
		var order = ordRepo.findById(id);
		if(order.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Orders>(order.get(), HttpStatus.FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Orders> postOrder(@RequestBody Orders order){
		if(order == null || order.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		order.setStatus("new");
		var ord = ordRepo.save(order);
		return new ResponseEntity<Orders>(ord, HttpStatus.CREATED);
	}
	
	//we get a warning if we don't enter anything in the angle bracket
	//lets us use response entity without a type
	@SuppressWarnings("rawtypes")
	//need to change url because we already have a url with an id in the put method
	@PutMapping("review/{id}")
	public ResponseEntity reviewOrder(@PathVariable int id, @RequestBody Orders order) {
		var statusValue = (order.getTotal() <= 50) ? "approved" : "review";
		order.setStatus(statusValue);
		return putOrder(id, order);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("approve/{id}")
	public ResponseEntity approveOrder(@PathVariable int id, @RequestBody Orders order) {
		order.setStatus("approved");
		return putOrder(id, order);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("reject/{id}")
	public ResponseEntity rejectOrder(@PathVariable int id, @RequestBody Orders order) {
		order.setStatus("rejected");
		return putOrder(id, order);
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity<Orders> putOrder(@PathVariable int id, @RequestBody Orders order){
		if(order == null || order.getId() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var ord = ordRepo.findById(order.getId());
		if(ord.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ordRepo.save(order);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteOrder(@PathVariable int id) {
		var order = ordRepo.findById(id);
		if(order.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ordRepo.delete(order.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}
