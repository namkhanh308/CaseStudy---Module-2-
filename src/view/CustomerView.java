package view;

import Account.AccountManagerment;
import controller.ShoppingCartManager;
import model.Customer;
import model.ShoppingCart;

import java.util.Scanner;

public class CustomerView {
    AccountManagerment accountManagerment = new AccountManagerment();
    String id;
    Customer customer;
    ShoppingCartManager shoppingCartManager = new ShoppingCartManager();

    public void displayFunction(){
        System.out.println("Xin chào bạn đã đến với Cửa hàng Food");
        System.out.println("1. Đăng nhập");
        System.out.println("2. Đăng ký");
        System.out.println("3. Hiển thị thông tin cá nhân");
        System.out.println("4. Hiển thị danh sách sản phẩm");
        System.out.println("5. Chọn sản phẩm vào giỏ hàng");
        System.out.println("6. Xem giỏ hàng của bạn");
        System.out.println("7. Sửa giỏ hàng của bạn");
        System.out.println("0. Thoát");
    }
    public void Register(){
         id = accountManagerment.addAccount();
         accountManagerment.displayCustomerForID(id);
    }
    public void login(){
        Customer customer1 = accountManagerment.login() ;
        if( customer1 != null){
            customer = customer1;
            id = customer.getId();
            System.out.println("Đăng nhập thành công");
        }
        else {
            System.out.println("Đăng nhập không thành công");
        }
    }
    public void Menu(){
        int choose = -1;
        while (choose != 0){
            displayFunction();
            Scanner scanner = new Scanner(System.in);
            choose = scanner.nextInt();
            switch (choose){
                case 1:
                    login();
                    break;
                case 2:
                    Register();
                    break;
                case 3:
                    displayInfo();
                    break;
                case 4:
                    shoppingCartManager.getProductList().displayProduct();
                    break;
                case 5:
                    shoppingCartManager.addShoppingCart(id);
                    break;
                case 6:
                    displayProductInMyCart(id);
                    break;
                case 7:
                    shoppingCartManager.editProductInShoppingCart(id);
                    break;
            }
        }
    }
    public void displayProductInMyCart(String id){
        try{
            if(id == null){
                System.out.println("Bạn chưa đăng nhập");
            }
            else {
                shoppingCartManager.displayProductInMyCart(customer.getId());
            }
        }
        catch (Exception e){
            System.out.println("Giỏ hàng của bạn không có gì");
        }
    }

    public void displayInfo() {
        System.out.println(customer);
    }

}
