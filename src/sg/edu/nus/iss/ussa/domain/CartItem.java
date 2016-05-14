/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.domain;

import sg.edu.nus.iss.ussa.util.StoreUtil;

/**
 *
 * @author Ajay
 */
public class CartItem {

    private Product product = null;
    private double price = 0;
    private int qty = 0;

    public CartItem(Product product, double price, int qty) {
        this.product = product;
        this.price = price;
        this.qty = qty;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double calcAmount() {
        return StoreUtil.mul(this.price, this.qty);
    }
}
