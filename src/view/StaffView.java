package view;

import Account.AccountManagerment;
import Account.AccountStaff;
import controller.ProductManagerment;
import model.Customer;
import model.Product;
import model.Staff;

import java.io.IOException;
import java.util.Scanner;

public class StaffView {
    ProductManagerment productManagerment = new ProductManagerment();
    String id;
    Staff staff;
    AccountManagerment accountManagerment = new AccountManagerment();

    public void displayFuntion(){
        System.out.println("Chế độ quản lý");
        System.out.println("1. Hiển thị thông tin tài khoản");
        System.out.println("2. Đổi mật khẩu tài khoản");
        System.out.println("3. Chế độ quản lý sản phẩm");
        System.out.println("4. Chế độ quản lý danh mục");
        System.out.println("5. Chế độ hiển thị khách hàng");
        System.out.println("0. Thoát.");
    }
    public void displayMainFuntion(){
        System.out.println("1. Đăng nhập tài khoản");
        System.out.println("2. Đăng ký tài khoản");
        System.out.println("0. Thoát");
    }
    public void displayFuntionProductManager(){
        System.out.println("1. Hiển thị sản phẩm hiện có");
        System.out.println("2. Thêm sản phẩm");
        System.out.println("3. Sửa sản phẩm");
        System.out.println("4. Xóa sản phẩm");
        System.out.println("0. Thoát");
    }
    public void displayFunctionTypeProduct(){
        System.out.println("1. Hiển thị loại sản phẩm hiện có");
        System.out.println("2. Thêm loại sản phẩm");
        System.out.println("3. Sửa loại sản phẩm");
        System.out.println("4. Xóa loại sản phẩm");
        System.out.println("0. Thoát");
    }
    public void Menu(){
        int choose = -1;
        while (choose != 0){
            displayMainFuntion();
            Scanner scanner = new Scanner(System.in);
            choose = scanner.nextInt();
            switch (choose){
                case 1:
                    loginStaff();
                    break;
                case 2:
                    displayAccountStaff();
                    break;
            }
        }
    }
    public void loginStaff(){
            Staff staff1 = accountManagerment.loginStaff() ;
            if( staff1 != null){
                staff = staff1;
                id = staff.getId();
                System.out.println("Đăng nhập thành công");
                System.out.println(staff);
                int choose = -1;
                while (choose != 0){
                    displayFuntion();
                    Scanner scanner = new Scanner(System.in);
                    choose = scanner.nextInt();
                    switch (choose){
                        case 1:
                            System.out.println("Chức năng hiển thị thông tin tài khoản");
                            System.out.println(staff);
                            break;
                        case 2:
                            System.out.println("Chức năng đổi mật khẩu tài khoản");
                            Scanner scanner1 = new Scanner(System.in);
                            System.out.println("Nhập mật khẩu mới mà bạn muốn đổi: ");
                            String passWord = scanner1.nextLine();
                            staff.getAccountStaff().setPassWord(passWord);
                            System.out.println("Đổi thành công");
                            break;
                        case 3:
                            System.out.println("Chế độ quản lý sản phẩm");
                            try {
                                ProductManager();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 4:
                            System.out.println("Chức năng quản lý loại sản phẩm");
                            try {
                                productTypeManager();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                }
            }
            else {
                System.out.println("Đăng nhập không thành công");
            }
    }

    public void ProductManager() throws IOException {
        int choose = -1;
        while (choose != 0){
            displayFuntionProductManager();
            Scanner scanner = new Scanner(System.in);
            choose = scanner.nextInt();
            switch (choose){
                case 1:
                    System.out.println("Chức năng hiển thị sản phẩm hiện có: ");
                    productManagerment.displayProduct();
                    break;
                case 2:
                    System.out.println("Chức năng thêm sản phẩm");
                    productManagerment.addProduct();
                    System.out.println("Thêm thành công");
                    break;
                case 3:
                    System.out.println("Chức năng sửa sản phẩm");
                    productManagerment.repairProduct();
                    break;
                case 4:
                    System.out.println("Chức năng xóa sản phẩm");
                    productManagerment.deleteProduct();
                    break;
            }
        }

    }
    public void productTypeManager() throws IOException {
        int choose = -1;
        while (choose != 0) {
            displayFuntionProductManager();
            Scanner scanner = new Scanner(System.in);
            choose = scanner.nextInt();
            switch (choose){
                case 1:
                    System.out.println("Chức năng hiển thị loại sản phẩm hiện có");
                    productManagerment.displayType();
                    break;
                case 2:
                    System.out.println("Chức năng thêm mới loại sản phẩm");
                    productManagerment.addTypeProduct();
                    break;
                case 3:
                    System.out.println("Chức năng sửa loại sản phẩm");

                    break;
                case 4:
                    System.out.println("Chức năng xóa loại sản phẩm");
                    productManagerment.deleteType();
                    break;
                case 5:
                    System.out.println("Thêm khuyến mãi cho loại sản phẩm");
                    productManagerment.addVoucherToType();
                    break;
            }
        }

    }
    public void displayAccountStaff(){
//        accountManagerment.displayAccountStaff();
        accountManagerment.displayStaff();
    }

}
