package model;

import controller.ProductManagerment;

import java.util.List;

public class Order {
    private int count;
    private ShoppingCart shoppingCart;
    public Order() {
    }

    public Order(int count, ShoppingCart shoppingCart) {
        this.count = count;
        this.shoppingCart = shoppingCart;
    }

    public int getTotalPrice(){
        int total = 0;
        for (Product product:shoppingCart.getProducts() ) {
            total += product.getPrice();
        }
        return total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Order{" +
                "count=" + count +
                ", shoppingCart=" + shoppingCart +
                '}';
    }
}
