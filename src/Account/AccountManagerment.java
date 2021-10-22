package Account;

import controller.CustomerManager;
import controller.ProductManagerment;
import controller.StaffManagerment;
import model.Customer;
import model.Staff;
import storage.ReadWriteFileAccount;
import storage.ReadWriteFileAccountStaff;
import storage.ReadWriteFileCustomer;
import storage.ReadWriteFileStaff;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AccountManagerment {
    List<AccountCustomer> accountCustomers;
    List<AccountStaff> accountStaffs;

    CustomerManager customerManager;
    StaffManagerment staffManagerment;
    private ReadWriteFileCustomer readWriteFileCustomer = ReadWriteFileCustomer.getInstance();
    private ReadWriteFileAccount readWriteFileAccount = ReadWriteFileAccount.getInstance() ;

    private ReadWriteFileStaff readWriteFileStaff = ReadWriteFileStaff.getInstance();
    private ReadWriteFileAccountStaff readWriteFileAccountStaff = ReadWriteFileAccountStaff.getInstance();

    public AccountManagerment() {
        try {
            this.accountStaffs = readWriteFileAccountStaff.readFile();
            this.accountCustomers = readWriteFileAccount.readFile();
            this.customerManager = new CustomerManager();
            this.staffManagerment = new StaffManagerment();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Customer chooseCustomer(String account){
        for (Customer customer : customerManager.getCustomers()) {
            if(customer.getEmail().equals(account)){
                return customer;
            }
        }
        return null;
    }
    public Staff chooseStaff(String account){
        for (Staff staff : staffManagerment.getStaffList()) {
            if(staff.getEmail().equals(account)){
                return staff;
            }
        }
        return null;
    }
    public Customer login(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chức năng đăng nhập");
        System.out.println("Mời bạn nhập vào tài khoản: ");
        String account = scanner.nextLine();
        System.out.println("Mời bạn nhập vào mật khẩu");
        String password = scanner.nextLine();
        if(validate(account,password)){
            System.out.println(chooseCustomer(account));
            return chooseCustomer(account);
        }
        else {
            return null;
        }
    }
    public Staff loginStaff(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chức năng đăng nhập");
        System.out.println("Mời bạn nhập vào tài khoản: ");
        String account = scanner.nextLine();
        System.out.println("Mời bạn nhập vào mật khẩu");
        String password = scanner.nextLine();
        if(validateStaff(account,password)){
            System.out.println(chooseStaff(account));
            return chooseStaff(account);
        }
        else {
            return null;
        }
    }
    public boolean validate(String account, String password){
        for (AccountCustomer accountCustomer : accountCustomers) {
            if(accountCustomer.getAccount().equals(account) && accountCustomer.getPassWord().equals(password)){
                return true;
            }
        }
        return false;
    }
    public boolean validateStaff(String account, String password){
        for (AccountStaff accountStaff : accountStaffs) {
            if(accountStaff.getAccount().equals(account) && accountStaff.getPassWord().equals(password)){
                return true;
            }
        }
        return false;
    }
    public Customer findAccoutCustomer(String idCus){
        for (Customer customer :customerManager.getCustomers()) {
            if(customer.getId().equals(idCus)){
                return customer;
            }
        }
        return null;
    }
    public void findAccountForEmail(String email, String newPassWord){
        for (AccountCustomer accountCustomer: accountCustomers) {
            if(accountCustomer.getAccount().equals(email)){
                accountCustomer.setPassWord(newPassWord);
            }
        }
    }

    public void resetPassWordCustomer(){
        customerManager.displayCustomer();
        System.out.println("Nhập vào id của khách hàng");
        Scanner scanner = new Scanner(System.in);
        String idCus = scanner.nextLine();
        Customer customer = findAccoutCustomer(idCus);
        if(customer != null){
            System.out.println(accountCustomers.get(0).getPassWord());
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Nhập vào mật khẩu bạn muốn đổi");
            String password = scanner1.nextLine();
            customer.getAccountCustomer().setPassWord(password);
            findAccountForEmail(customer.getEmail(),password);
            System.out.println("Đổi thành công");
            try {
                readWriteFileAccount.writeFile(accountCustomers);
                readWriteFileCustomer.writeFile(customerManager.getCustomers());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Bạn nhập sai id khách hàng");
        }
    }
    public int findAccoutStaff(String email){
        for (int i = 0; i < staffManagerment.getStaffList().size(); i++) {
            if(staffManagerment.getStaffList().get(i).getAccountStaff().getAccount().equals(email)){
                return i;
            }
        }
        return -1;
    }
    public String addAccount(){
        String idCustomer;
        System.out.println("Chức năng thêm acount: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap vao tai khoan");
        String account = scanner.nextLine();
        System.out.println("Nhap vao mat khau");
        String password = scanner.nextLine();
        System.out.println("Nhap vào tên của bạn");
        String name = scanner.nextLine();
        System.out.println("Nhap vao địa chỉ");
        String adress = scanner.nextLine();

        AccountCustomer accountCustomer = new AccountCustomer(account,password);
        accountCustomers.add(accountCustomer);
        String id = String.valueOf(customerManager.getCustomers().size());
        customerManager.getCustomers().add(new Customer(accountCustomer,id,name,adress,account));
        try {
            readWriteFileAccount.writeFile(accountCustomers);
            readWriteFileCustomer.writeFile(customerManager.getCustomers());
        } catch (IOException e) {
            e.printStackTrace();
        }
        idCustomer = id;
        return idCustomer;
    }
    public String insertAccountStaff(){
        String idStaff ;
        System.out.println("Chức năng thêm acount: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap vao tai khoan");
        String account = scanner.nextLine();
        System.out.println("Nhap vao mat khau");
        String password = scanner.nextLine();
        System.out.println("Nhap vào tên của bạn:");
        String name = scanner.nextLine();
        System.out.println("Nhập vào tuổi của bạn:");
        String old = scanner.nextLine();
        AccountStaff accountStaff = new AccountStaff(account,password);
        accountStaffs.add(accountStaff);
        String id = String.valueOf(staffManagerment.getStaffList().size());
        staffManagerment.getStaffList().add(new Staff(id,name,old,account,accountStaff));
        try {
            readWriteFileAccountStaff.writeFile(accountStaffs);
            readWriteFileStaff.writeFile(staffManagerment.getStaffList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        idStaff = id;
        return idStaff;
    }
    public void displayAccountStaff(){
        for (AccountStaff accountStaff : accountStaffs ) {
            System.out.println(accountStaff);
        }
    }
    public void displayStaff(){
        for (Staff staff : staffManagerment.getStaffList()) {
            System.out.println(staff);
        }
    }

    public void displayCustomerForID(String id){
        for (Customer customer :customerManager.getCustomers() ) {
            if(customer.getId().equals(id)){
                System.out.println(customer);
            }
        }
    }
    public void displayAccount(){
        for (AccountCustomer accountCustomer :accountCustomers ) {
            System.out.println(accountCustomer);
        }
    }
    public void displayCustomer(){
        for (Customer customer :customerManager.getCustomers() ) {
            System.out.println(customer);
        }
    }


    public CustomerManager getCustomerManager() {
        return customerManager;
    }

    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    public List<AccountCustomer> getAccountCustomers() {
        return accountCustomers;
    }

    public void setAccountCustomers(List<AccountCustomer> accountCustomers) {
        this.accountCustomers = accountCustomers;
    }

    public List<AccountStaff> getAccountStaffs() {
        return accountStaffs;
    }

    public void setAccountStaffs(List<AccountStaff> accountStaffs) {
        this.accountStaffs = accountStaffs;
    }

    public StaffManagerment getStaffManagerment() {
        return staffManagerment;
    }

    public void setStaffManagerment(StaffManagerment staffManagerment) {
        this.staffManagerment = staffManagerment;
    }
}
