/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.domain;

/**
 *
 * @author a0134601w
 */
public class Product {

    private String productId;
    private Category category;
    private String name;
    private String briefDescription;
    private int quantityAvailable;
    private double price;
    private String barCode;
    private int reorderQuantity;
    private int orderQuantity;

    public Product(Category category, String name,
            String briefDescription, int quantityAvailable, double price,
            String barCode, int recorderQuantity, int orderQuantity) {
        super();

        this.category = category;
        this.name = name;
        this.briefDescription = briefDescription;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
        this.barCode = barCode;
        this.reorderQuantity = recorderQuantity;
        this.orderQuantity = orderQuantity;
    }

    public Product(String productId, Category category, String name,
            String briefDescription, int quantityAvailable, double price,
            String barCode, int recorderQuantity, int orderQuantity) {
        super();
        this.productId = productId;
        this.category = category;
        this.name = name;
        this.briefDescription = briefDescription;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
        this.barCode = barCode;
        this.reorderQuantity = recorderQuantity;
        this.orderQuantity = orderQuantity;
    }

    public boolean checkInventoryLevel() {
        if (this.quantityAvailable < this.reorderQuantity) {
            return false;
        }
        return true;
    }

    public boolean compare(Product p) {
        if (name.equals(p.getName()) && briefDescription.equals(p.getBriefDescription()) && category.equals(p.getCategory())
                && price == p.getPrice() && barCode.equals(p.getBarCodeNumber()) && reorderQuantity == p.getReorderQuantity()
                && orderQuantity == p.getOrderQuantity()) {
            return true;
        }
        return false;
    }

    public void addQuantity(int add) {
        this.quantityAvailable = this.quantityAvailable + add;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBarCodeNumber() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public int getReorderQuantity() {
        return reorderQuantity;
    }

    public void setRecorderQuantity(int recorderQuantity) {
        this.reorderQuantity = recorderQuantity;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public void show() {
        System.out.println(name);
    }

}
