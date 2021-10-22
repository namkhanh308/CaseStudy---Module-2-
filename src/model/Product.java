package model;

import java.io.Serializable;

public class Product implements Serializable {
    private String id;
    private String name;
    private Type type;
    private String supplier;
    private int quantity;
    private double price;
    public Product() {
    }
    public Product(String id, String name, Type type, String supplier, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.supplier = supplier;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price* quantity - this.price* quantity * type.getDiscount() ;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Sản phẩm có id: "+ this.getId() + " / " + "Tên: " + this.getName() +" / "+"Loại sản phẩm: " + this.getType().getTypeName() + " / " + "Khuyến mãi: " + this.getType().getDiscount()+  " / " + "Nhà cung cấp: "+ this.getSupplier() +" / "+ "Giá: " + this.getPrice() + " / " + "Số lượng: " + this.getQuantity();
    }
}
