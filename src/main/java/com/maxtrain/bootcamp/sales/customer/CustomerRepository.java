package com.maxtrain.bootcamp.sales.customer;

import org.springframework.data.repository.CrudRepository;

//this is initializing our customer repo
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
