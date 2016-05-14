/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.domain;

import java.util.ArrayList;


/**
 *
 * @author Ajay
 */
public class Transaction {

    private int id = 0;

    private String date;
    private Customer customer;
    private int quantity;
    private Product product;

    private double cashAmount = 0;
    private double total = 0;

    public Transaction() {
    }

    public Transaction(int id, Product prod, Customer customer, int quantity, String date, double cashAmount) {
        this.id = id;
        this.customer = customer;
        this.quantity = quantity;
        this.date = date;
        this.product = prod;
        this.cashAmount = cashAmount;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    

    
    public Customer getCustomer() {
        return customer;
    }

    public void setProduct(Product prod) {
        this.product = prod;
    }

    public Product getProduct() {
        return product;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }
}
