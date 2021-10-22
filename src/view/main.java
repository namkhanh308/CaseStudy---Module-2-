package view;

import Account.Account;
import controller.BillManagerment;
import controller.CustomerManager;
import controller.ProductManagerment;
import controller.ShoppingCartManager;
import model.Bill;
import model.Customer;
import model.ShoppingCart;

import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        int choose = -2;
        while (choose != 0) {
            System.out.println("1. Nhân Viên");
            System.out.println("2. Khách hàng");
            System.out.println("0. Thoát");
            System.out.println("Chọn");
            Scanner scanner = new Scanner(System.in);
            choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    StaffView staffView = new StaffView();
                    staffView.Menu();
                    break;
                case 2:
                    CustomerView customerView = new CustomerView();
                    customerView.Menu();
                    break;
            }
        }
    }
////        CustomerManager customerManager = new CustomerManager();
////        customerManager.displayCustomer();
//        ProductManagerment productManagerment = new ProductManagerment();
//        productManagerment.displayProduct();
    }

