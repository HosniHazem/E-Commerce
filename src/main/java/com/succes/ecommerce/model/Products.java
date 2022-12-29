package com.succes.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Entity
@Table(name="products")
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String name;
	Long price;
	String added_on;


	 String description;


	 int discount;


	int quantity;


	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "category_id")
	Category category;

	String images;

	@ManyToMany
	List<Order> orders;

	@ManyToOne
	@JoinColumn(name="shop_id")
	Shop shop;



	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}




	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getAdded_on() {
		return added_on;
	}
	public void setAdded_on(String added_on) {
		this.added_on = added_on;
	}
	public Category getCategory_id() {
		return category;
	}
	public void setCategory_id(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
