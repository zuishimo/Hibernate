package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	public int id;
	public String name;
	public float price;
	
	public void setId(int id) {
		this.id = id;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	@Column(name="price")
	public float getPrice() {
		return price;
	}
}
