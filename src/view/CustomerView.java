package view;

import Account.AccountManagerment;
import controller.CustomerManager;
import controller.ShoppingCartManager;
import model.Customer;
import model.ShoppingCart;
import storage.ReadWriteFileCustomer;
import storage.ReadWriteFileShoppingCart;

import java.io.IOException;
import java.util.Scanner;

public class CustomerView {
    AccountManagerment accountManagerment = new AccountManagerment();
    String id;
    Customer customer;

    CustomerManager customerManager = new CustomerManager();
    ShoppingCartManager shoppingCartManager = new ShoppingCartManager();
    ReadWriteFileShoppingCart readWriteFileShoppingCart = ReadWriteFileShoppingCart.getInstance();
    ReadWriteFileCustomer readWriteFileCustomer = ReadWriteFileCustomer.getInstance();

    public void displayFunction(){
        System.out.println("......................................");
        System.out.println("Xin chào bạn đã đến với Cửa hàng Food");
        System.out.println("1. Đăng nhập");
        System.out.println("2. Đăng ký");
        System.out.println("3. Hiển thị thông tin cá nhân");
        System.out.println("4. Hiển thị danh sách sản phẩm");
        System.out.println("5. Chọn sản phẩm vào giỏ hàng");
        System.out.println("6. Xem giỏ hàng của bạn");
        System.out.println("7. Sửa giỏ hàng của bạn");
        System.out.println("8. Kiểm tra số dư trong tài khoản");
        System.out.println("9. Nạp tiền vào tài khoản");
        System.out.println("10. Yêu cầu thanh toán");
        System.out.println("0. Thoát");
        System.out.println("......................................");
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
                    System.out.println("Giỏ hàng của bạn có");
                    displayProductInMyCart(id);
                    break;
                case 7:
                    shoppingCartManager.editProductInShoppingCart(id);
                    break;
                case 8:
                    checkAmount();
                    break;
                case 9:
                    depositMoney();
                    break;
                case 10:
                    paymentRequest();
                    break;
            }
        }
    }

    public void paymentRequest(){
        int indexShoppingCart = shoppingCartManager.findIndexShoppingCart(id);
        shoppingCartManager.getShoppingCartList().get(indexShoppingCart).paymentStatus = true;
        try {
            readWriteFileShoppingCart.writeFile(shoppingCartManager.getShoppingCartList());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void checkAmount(){
        System.out.println("Số dư trong tài khoản của bạn là: " + customerManager.getCustomers().get(findIndexCustomer(id)).getAmount_available() + " đồng") ;
    }
    public void depositMoney(){
        System.out.println("Chức năng nạp tiền vào tài khoản");
        Scanner scanner = new Scanner(System.in);
        double money = scanner.nextDouble();
        customerManager.getCustomers().get(findIndexCustomer(id)).setAmount_available(customerManager.getCustomers().get(findIndexCustomer(id)).getAmount_available() + money);
        shoppingCartManager.getShoppingCartList().get(shoppingCartManager.findShopingCart(id)).getCustomer().setAmount_available(customerManager.getCustomers().get(findIndexCustomer(id)).getAmount_available());
        System.out.println("Nạp tiền thành công");
        for (Customer customer:customerManager.getCustomers()) {
            System.out.println(customer);
        }
        try {
            readWriteFileShoppingCart.writeFile(shoppingCartManager.getShoppingCartList());
            readWriteFileCustomer.writeFile(customerManager.getCustomers());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public int findIndexCustomer(String idCustomer){
        int index = -1;
        for (int i = 0; i < customerManager.getCustomers().size(); i++) {
            if(customerManager.getCustomers().get(i).getId().equals(id)){
                index = i;
            }
        }
        return index;
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
