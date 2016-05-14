/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.domain;

import java.util.ArrayList;

/**
 *
 * @author a0134449b
 */
public class Category {

    private String categorycode;
    private String categoryname;
    private ArrayList<Vendor> vendorList;

    public Category(String categorycode, String categoryname, ArrayList<Vendor> vendorList) {
        super();
        this.categorycode = categorycode;
        this.categoryname = categoryname;
        this.vendorList = vendorList;
    }

    public Category(String categorycode, String categoryname) {
        super();
        this.categorycode = categorycode;
        this.categoryname = categoryname;

    }

    public void deleteVendor(String name) {
        if (name == "") {
            return;
        } else {
            if (this.vendorList.isEmpty()) {
                System.out.println("The Vendor list is empty asshole!");
            }
            for (Vendor i : this.vendorList) {
                if (i.getVendorName() == name) {
                    vendorList.remove(i);
                }
            }
        }
    }

    public Vendor getFirstVendor() {
        Vendor vendor = null;
        if (!this.vendorList.isEmpty()) {
            vendor = this.vendorList.get(0);
        }
        return vendor;
    }

    public String getCategoryCode() {
        return categorycode;
    }

    public void setCategoryCode(String code) {
        this.categorycode = code;
    }

    public String getCategoryName() {
        return categoryname;
    }

    public void setCategoryName(String name) {
        this.categoryname = name;
    }

    public ArrayList<Vendor> getVendorList() {
        return vendorList;
    }

    public void setVendorList(ArrayList<Vendor> vendorList) {
        this.vendorList = vendorList;
    }

}
