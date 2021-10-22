package model;

import controller.ProductManagerment;

import java.io.Serializable;
import java.util.List;

public class Bill implements Serializable {
    private String id;
    private int count;
    private ShoppingCart shoppingCart;
    public Bill() {
    }

    public Bill(String id, int count, ShoppingCart shoppingCart) {
        this.id = id;
        this.count = count;
        this.shoppingCart = shoppingCart;
    }

    public Bill(String id, ShoppingCart shoppingCart) {
        this.id = id;
        this.count = 0;
        this.shoppingCart = shoppingCart;
    }


    public int getTotalPrice(){
        int total = 0;
        for (Product product:shoppingCart.getProducts() ) {
            total += product.getPrice();
        }
        return total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public String toString() {
        return "Bill có id: " + this.getId() + " / " + "Tên khách hàng: " + this.getShoppingCart().getCustomer().getName() + " / " + "Sản phẩm đã mua: " + this.shoppingCart.getProducts() + " / " + "Số tiền đã thanh toán: " + this.getCount() +" / "+ "Trạng thái thanh toán: Đã thanh toán" ;
    }
}
