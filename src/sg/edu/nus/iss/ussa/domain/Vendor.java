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
public class Vendor {

    private String VendorName;
    private String VendorDescription;

    public String getVendorName() {
        return VendorName;
    }

    public String getVendorDescription() {
        return VendorDescription;
    }

    public Vendor(String VendorName, String VendorDescription) {
        this.VendorName = VendorName;
        this.VendorDescription = VendorDescription;
    }
}
