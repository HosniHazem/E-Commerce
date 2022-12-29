package com.succes.ecommerce.model;



import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="coupon")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

     String key_name;

     boolean active;

     int discountAmount;
     Date expDate;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop coupons;

    public String getKey_name() {
        return key_name;
    }

    public void setKey_name(String key_name) {
        this.key_name = key_name;
    }

    public Shop getCoupons() {
        return coupons;
    }

    public void setCoupons(Shop coupons) {
        this.coupons = coupons;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key_name;
    }

    public void setKey(String key) {
        this.key_name = key;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }



}
