package view;

import Account.AccountManagerment;
import Account.AccountStaff;
import controller.*;
import model.Customer;
import model.Product;
import model.ShoppingCart;
import model.Staff;
import storage.ReadWriteFileAccountStaff;
import storage.ReadWriteFileShoppingCart;
import storage.ReadWriteFileStaff;

import java.io.IOException;
import java.util.Scanner;

public class StaffView {
    ProductManagerment productManagerment = new ProductManagerment();
    ShoppingCartManager shoppingCartManager = new ShoppingCartManager();
    BillManagerment billManagerment = new BillManagerment();
    CustomerManager customerManager = new CustomerManager();
    StaffManagerment staffManagerment = new StaffManagerment();

    String id;
    Staff staff;
    AccountManagerment accountManagerment = new AccountManagerment();
    ReadWriteFileShoppingCart readWriteFileShoppingCart = ReadWriteFileShoppingCart.getInstance();
    ReadWriteFileAccountStaff readWriteFileAccountStaff = ReadWriteFileAccountStaff.getInstance();
    ReadWriteFileStaff readWriteFileStaff = ReadWriteFileStaff.getInstance();

    public void displayFuntion(){
        System.out.println("...................................");
        System.out.println("Chế độ quản lý");
        System.out.println("1. Hiển thị thông tin tài khoản");
        System.out.println("2. Đổi mật khẩu tài khoản");
        System.out.println("3. Chế độ quản lý sản phẩm");
        System.out.println("4. Chế độ quản lý danh mục");
        System.out.println("5. Chế độ thanh toán");
        System.out.println("6. Chế độ quản lý thông tin khách hàng");
        System.out.println("0. Thoát.");
        System.out.println("...................................");
    }
    public void displayMainFuntion(){
        System.out.println("...................................");
        System.out.println("1. Đăng nhập tài khoản");
        System.out.println("2. Đăng ký tài khoản");
        System.out.println("0. Thoát");
        System.out.println("...................................");
    }
    public void displayFuntionProductManager(){
        System.out.println("...................................");
        System.out.println("1. Hiển thị sản phẩm hiện có");
        System.out.println("2. Thêm sản phẩm");
        System.out.println("3. Sửa sản phẩm");
        System.out.println("4. Xóa sản phẩm");
        System.out.println("0. Thoát");
        System.out.println("...................................");
    }
    public void displayFunctionTypeProduct(){
        System.out.println("...................................");
        System.out.println("1. Hiển thị loại sản phẩm hiện có");
        System.out.println("2. Thêm loại sản phẩm");
        System.out.println("3. Sửa loại sản phẩm");
        System.out.println("4. Xóa loại sản phẩm");
        System.out.println("0. Thoát");
        System.out.println("...................................");
    }
    public void displayFunctionCustomer(){
        System.out.println("...................................");
        System.out.println("1. Hiển thị thông tin khách hàng ");
        System.out.println("2. Quản lý tài khoản khách hàng");
        System.out.println("3. Hiển thị số sản phẩm đã mua của khách hàng");
        System.out.println("4. Thống kê theo tiền khách hàng đã mua");
        System.out.println("0. Thoát");
        System.out.println("Chọn");
        System.out.println("...................................");
    }
    public void displayFunctionPayment(){
        System.out.println("...................................");
        System.out.println("1. Kiểm tra danh sách các giỏ hàng chờ thanh toán");
        System.out.println("2. Chức năng thanh toán/ Tạp bill");
        System.out.println("3. Hiển thị bill của khách hàng");
        System.out.println("0. Thoát");
        System.out.println("Chọn");
        System.out.println("...................................");
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
                    Register();
                    break;
            }
        }
    }
    public void Register(){
        id = accountManagerment.insertAccountStaff();
    }
    public void loginStaff(){
            Staff staff1 = accountManagerment.loginStaff() ;
            if( staff1 != null){
                staff = staff1;
                id = staff.getId();
                System.out.println("Đăng nhập thành công");
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
                            int index = accountManagerment.findAccoutStaff(staff.getEmail());
                            accountManagerment.getStaffManagerment().getStaffList().get(index).getAccountStaff().setPassWord(passWord);
                            try {
                                readWriteFileAccountStaff.writeFile(accountManagerment.getAccountStaffs());
                                readWriteFileStaff.writeFile(staffManagerment.getStaffList());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
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
                        case 5:
                            System.out.println("Chức năng thanh toán sản phẩm");
                            PaymentProduct();
                            break;
                        case 6:
                            System.out.println("Chức năng quản lý thông tin khách hàng");
                            FuntionCustomerManager();
                            break;
                    }
                }
            }
            else {
                System.out.println("Đăng nhập không thành công");
            }
    }

    public void FuntionCustomerManager(){
        int choose = -1;
        while (choose != 0){
            displayFunctionCustomer();
            Scanner scanner = new Scanner(System.in);
            choose = scanner.nextInt();
            switch (choose){
                case 1:
                    System.out.println("Chức năng hiển thị thông tin khách hàng");
                    customerManager.displayCustomer();
                    break;
                case 2:
                    System.out.println("Chức năng quản lý tài khoản của khách hàng");
                    customerManager();
                    break;
                case 3:
                    System.out.println("Chức năng hiển thị số sản phẩm đã mua của khách hàng ");
                    Scanner scanner1 = new Scanner(System.in);
                    String idCus = scanner1.nextLine();
                    System.out.println("Mời bạn nhập vào id của khách hàng");
                    billManagerment.displayBillForId(idCus);
                    break;
                case 4:
                    System.out.println("Chức năng thống kê tiền theo khách hàng");
                    Scanner scanner2 = new Scanner(System.in);
                    String inCus1 = scanner2.nextLine();
                    System.out.println("Mời bạn nhập vào id của khách hàng");
                    billManagerment.dislayTotalMoneyForCustomer(inCus1);
                    break;
            }
        }
    }
    public void customerManager(){
        int choose = -1;
        while (choose != 0){
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Đổi mật khẩu của khách hàng");
            System.out.println("2. Sửa thông tin của khách hàng");
            choose = scanner.nextInt();
            switch (choose){
                case 1:
                    System.out.println("Chức năng đổi mật khẩu khách hàng");
                    accountManagerment.resetPassWordCustomer();
                    break;
                case 2:
                    System.out.println("Chức năng sửa thông tin khách hàng");
                    System.out.println("Comming soon ^^.");
                    break;
            }
        }
    }

    public void PaymentProduct() {
        int choose = -1;
        while (choose != 0) {
            displayFunctionPayment();
            Scanner scanner = new Scanner(System.in);
            choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("Chức năng hiển thị sản phẩm hiện có: ");
                    displayPaymentStatus();
                    break;
                case 2:
                    System.out.println("Chức năng thanh toán");
                    payMoneyForCustomer();
                    break;
                case 3:
                    System.out.println("Chức năng hiển thị hóa đơn khách hàng");
                    displayBillForID();
                    break;
            }

        }
    }
    public void displayBillForID(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập vào id của khách hàng");
        String id = scanner.nextLine();
        billManagerment.displayBillForId(id);
    }
    public void displayPaymentStatus(){
        for (ShoppingCart shoppingCart :shoppingCartManager.getShoppingCartList() ) {
            if(shoppingCart.paymentStatus == true){
                System.out.println(shoppingCart);
            }

        }
    }
    public void payMoneyForCustomer(){
        System.out.println("Danh sách sản phẩm hiện có là: ");
        displayPaymentStatus();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Mời bạn nhập vào id của khách hàng cần thanh toán");
        String id = scanner.nextLine();
        shoppingCartManager.findShoppingCart(id);
        billManagerment.Pay(id,shoppingCartManager.findShoppingCart(id).getCustomer().getAmount_available());
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
                    System.out.println("Comming Soon ^^");
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
        accountManagerment.displayStaff();
    }

}
