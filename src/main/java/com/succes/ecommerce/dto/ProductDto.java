package com.succes.ecommerce.dto;

import com.succes.ecommerce.model.Category;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class ProductDto {
    // for create it can be optional
    // for update we need the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private @NotNull String name;

    private @NotNull String image_name;
    private @NotNull Long price;
    private @NotNull String added_on;
    private @NotNull Category category_id;


    public ProductDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_name() {
        return image_name;
    }

    public void getImage_name(String imageURL) {
        this.image_name = image_name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price= price;
    }

    public String getAdded_on() {
        return added_on;
    }

    public void setAdded_on(String added_on) {
        this.added_on = added_on;
    }

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategoryId(String categoryId) {
        this.category_id = category_id;
    }
    public void setCategory_Id(Long categoryId) {
        this.category_id = category_id;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
