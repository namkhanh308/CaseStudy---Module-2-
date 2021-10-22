package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements Serializable {
    String id;
    Customer customer ;
    List<Product> products;
    public boolean paymentStatus ;

    public ShoppingCart(String id, Customer customer, List<Product> products) {
        this.id = id;
        this.customer = customer;
        this.products = products;
        paymentStatus = false;
    }

    public ShoppingCart(String id, Customer customer) {
        this.id = id;
        this.customer = customer;
        this.products = new ArrayList<>();
    }
    public double getTotalPrice(){
        double sum = 0 ;
        for (Product product : products ) {
            sum += product.getPrice();
        }
        return sum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Giỏ hàng có id: " + this.getId() + " / "+ "Khách hàng có id là: " + this.getCustomer().getId() + " / " + "Tên khách hàng: " + this.getCustomer().getName() +" / "+ "Tiền có trong tài khoản: " + this.getCustomer().getAmount_available() + " / " + "Sản phẩm hiện có trong giỏ hàng: " + this.getProducts() + " / " + "Tiền tạm tính: " + this.getTotalPrice();
    }
}
