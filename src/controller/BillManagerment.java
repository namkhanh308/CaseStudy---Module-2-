package controller;

import model.Bill;
import model.Customer;
import model.Product;
import model.ShoppingCart;
import storage.ReadWriteFileBill;
import storage.ReadWriteFileCustomer;
import storage.ReadWriteFileShoppingCart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BillManagerment {
    private String id;
    ShoppingCartManager shoppingCartManager;
    CustomerManager customerManager = new CustomerManager();
    List<Bill> billList = new ArrayList<>();
    ReadWriteFileBill readWriteFileBill = ReadWriteFileBill.getInstance();
    ReadWriteFileShoppingCart readWriteFileShoppingCart =ReadWriteFileShoppingCart.getInstance();
    ReadWriteFileCustomer readWriteFileCustomer = ReadWriteFileCustomer.getInstance();
    public BillManagerment() {
        shoppingCartManager = new ShoppingCartManager();
        try {
            billList = readWriteFileBill.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public BillManagerment(String id, ShoppingCartManager shoppingCartManager, List<Bill> billList) {
        this.id = id;
        this.shoppingCartManager = shoppingCartManager;
        this.billList = billList;
    }
    public ShoppingCart findShoppingCart(String id){
        for (ShoppingCart shoppingCart:shoppingCartManager.getShoppingCartList()) {
            if(shoppingCart.getCustomer().getId().equals(id)){
                ShoppingCart shoppingCart1 = new ShoppingCart(shoppingCart.getId(),shoppingCart.getCustomer(),shoppingCart.getProducts());
                return shoppingCart1;
            }
        }
        return null;
    }
    public ShoppingCart findShoppingCartInstant(String id){
        for (ShoppingCart shoppingCart:shoppingCartManager.getShoppingCartList()) {
            if(shoppingCart.getCustomer().getId().equals(id)){
                return shoppingCart;
            }
        }
        return null;
    }
    public Customer findCustomer(String id){
        for (Customer customer: shoppingCartManager.getCustomerManager().getCustomers()) {
            if(customer.getId().equals(id)){
                return customer;
            }
        }
        return null;
    }

    public void createBill(String id, ShoppingCart shoppingCart){
        if(shoppingCart != null){
            String idBill = String.valueOf(billList.size());
            billList.add(new Bill(idBill, (int) shoppingCart.getTotalPrice(),shoppingCart));
            findShoppingCartInstant(id).setProducts(new ArrayList<>());
            System.out.println("Nhập thành công");
            int indexShoppingCart = shoppingCartManager.findIndexShoppingCart(id);
            shoppingCartManager.getShoppingCartList().get(indexShoppingCart).setPaymentStatus(false);
            Customer customer = findCustomer(id);
            customer.setAmount_available(customer.getAmount_available()- shoppingCart.getTotalPrice());
            for (ShoppingCart shoppingCart1 :shoppingCartManager.getShoppingCartList()) {
                System.out.println(shoppingCart1);
            }
            try {
                readWriteFileCustomer.writeFile(customerManager.getCustomers());
                readWriteFileShoppingCart.writeFile(shoppingCartManager.getShoppingCartList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Bạn nhập id không đúng");
        }
    }

    public void Pay(String id, double MoneyCustomerPaid){
        ShoppingCart shoppingCart = findShoppingCart(id);
        System.out.println(shoppingCart);
        double MoneyToBePaid = shoppingCart.getTotalPrice();
        boolean check = false;
        if(MoneyCustomerPaid > MoneyToBePaid){
            System.out.println("Khách trả thừa " + (MoneyCustomerPaid-MoneyToBePaid));
            check = true;
        }
        else if(MoneyCustomerPaid == MoneyToBePaid){
            System.out.println("Thanh toán thành công: ");
            check = true;
        }
        else {
            System.out.println("Bạn trả thiếu tiền");
        }

        if(check){
            createBill(id,shoppingCart);
            System.out.println("Tạo hóa đơn thành công");
        }
        else {
            System.out.println("Tạo hóa đơn thất bại");
        }
        try {
            readWriteFileBill.writeFile(billList);
            readWriteFileShoppingCart.writeFile(shoppingCartManager.getShoppingCartList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayBill(){
        for (Bill bill : billList) {
            System.out.println(bill);
        }
    }
    public void displayBillForId(String id){
        for (Bill bill:billList) {
            if(bill.getShoppingCart().getCustomer().getId().equals(id)){
                System.out.println(bill);
            }
        }
    }
    public void dislayTotalMoneyForCustomer(String id){
        int count = 0;
        double price = 0;
        Customer customer = new Customer();
        for (int i = 0; i < billList.size(); i++) {
            if(billList.get(i).getShoppingCart().getCustomer().getId().equals(id)){
                count++;
                price+= billList.get(i).getCount();
                customer = billList.get(i).getShoppingCart().getCustomer();
            }
        }
        System.out.println("Khách hàng " + customer.getName() + " đã mua " + count + " lần, Tổng số tiền thu được là: "+ price );
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ShoppingCartManager getShoppingCartManager() {
        return shoppingCartManager;
    }

    public void setShoppingCartManager(ShoppingCartManager shoppingCartManager) {
        this.shoppingCartManager = shoppingCartManager;
    }

    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
    }
}
