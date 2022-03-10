package com.maxtrain.bootcamp.sales.orders;
import org.springframework.data.repository.CrudRepository;


public interface OrdersRepository extends CrudRepository<Orders, Integer> {
	//find all orders in review
	//using naming conventions it searches through data and finds all orders with the status we pass into the parameter
	Iterable<Orders> findByStatus(String status);

}