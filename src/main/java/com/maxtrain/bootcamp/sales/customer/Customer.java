package com.maxtrain.bootcamp.sales.customer;

//entity and table are both classes in the package name
//using a * to load all classes instead of loading them individually
import javax.persistence.*;

//these are called annotations, start with an @ sign
//@table is creating a unique column
@Entity 
@Table(uniqueConstraints=@UniqueConstraint(name="UIDX_code", columnNames= {"code"}))

public class Customer {
	//@Id is making id a primary key
	@Id
	//generatedvalue is creating a unique value 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//@column is setting max length to 30 and does not accept a null value
	//this is the Required and StringLength equivalent in c#
	@Column(length=30, nullable=false)
	private String code;
	@Column(length=30, nullable=false)
	private String name;
	//sets the decimal places and the default value
	@Column(columnDefinition="decimal(9,2) NOT NULL DEFAULT 0.0")
	private double sales;
	private boolean active;
	
	
	
	public Customer() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSales() {
		return sales;
	}
	public void setSales(double sales) {
		this.sales = sales;
	}
	//changed isActive to getActive to make it consistent with everything else
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

}
