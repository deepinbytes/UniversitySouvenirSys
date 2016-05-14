/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.domain;

/**
 *
 * @author a0134449b
 */
public class Customer {

    private  String CustomerName="PUBLIC";

    public Customer() {

    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public Customer(String CustomerName) {
        this.CustomerName = CustomerName;
    }

}
