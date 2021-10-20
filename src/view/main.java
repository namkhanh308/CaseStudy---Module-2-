package view;

import Account.AccountCustomer;
import Account.AccountManagerment;
import controller.CustomerManager;
import controller.ProductManagerment;
import controller.ShoppingCartManager;
import controller.StaffManagerment;
import model.*;
import storage.ReadWriteFileProduct;
import storage.ReadWriteFileType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException {
//        AccountCustomer accountCustomer = new AccountCustomer("dmloc","dmloc");
//        Customer loc = new Customer(accountCustomer,"1","loc","HN");
////        List<Product> productList = new ArrayList<>();
////        ShoppingCartManager shoppingCartManager = new ShoppingCartManager();
////        shoppingCartManager.getProductList().displayProduct();

//        ShoppingCartManager shoppingCartManager = new ShoppingCartManager();
//        shoppingCartManager.displayShoppingCart();
//        shoppingCartManager.editProductInShoppingCart("0");
//        shoppingCartManager.getCustomerManager().displayCustomer();
//        shoppingCartManager.findShoppingCart("0");
//        shoppingCartManager.displayShoppingCart();
//        shoppingCartManager.getProductList().displayProduct();
//        shoppingCartManager.addShoppingCart("0");
//        shoppingCartManager.displayShoppingCart();

//        AccountManagerment accountManagerment = new AccountManagerment();
//        accountManagerment.displayAccount();
//        accountManagerment.displayCustomer();
//        accountManagerment.addAccount();
//        CustomerManager customerManager = new CustomerManager();
//        customerManager.displayCustomer();
//        customerManager.editCustomer();
//        customerManager.displayCustomer();
//        CustomerView customerView = new CustomerView();
//        customerView.Menu();
//          AccountManagerment accountManagerment = new AccountManagerment();
//        accountManagerment.displayAccountStaff();
//        accountManagerment.displayStaff();
          StaffView staffView = new StaffView();
          staffView.Menu();
//          accountManagerment.insertAccountStaff();
//          accountManagerment.displayAccountStaff();
//          accountManagerment.displayStaff();

    }
}
