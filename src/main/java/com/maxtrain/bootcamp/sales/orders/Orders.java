package com.maxtrain.bootcamp.sales.orders;
import javax.persistence.*;
import com.maxtrain.bootcamp.sales.customer.Customer;
@Entity

public class Orders {
	
	@Id
	private int id;
	@Column(length=30, nullable=false)
	private String description;
	@Column(columnDefinition="decimal(9,2) NOT NULL DEFAULT 0.0")
	private double total;
	
	//this is creating the foreign key to Customer
	//saying there are many orders for one customer
	//optional = false means it cant be null
	//@joincolumn will build the table on customerId column
	@ManyToOne(optional=false)
	@JoinColumn(name="customerId")
	private Customer customer;
	
	public Orders() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
}
