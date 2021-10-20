package controller;

import model.Customer;
import storage.ReadWriteFileCustomer;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CustomerManager {
    List<Customer> customers;
    private ReadWriteFileCustomer readWriteFileCustomer = ReadWriteFileCustomer.getInstance();
    public CustomerManager() {
        try {
            customers = readWriteFileCustomer.readFile();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public CustomerManager(List<Customer> customers) {
        this.customers = customers;
    }
    public void addCustomer(){
        System.out.println("Chức năng thêm khách hàng");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id khách hàng ");
        String id = scanner.nextLine();
        System.out.println("Nhập tên khách hàng ");
        String name = scanner.nextLine();
        System.out.println("Nhập địa chỉ khách hàng ");
        String adress = scanner.nextLine();
        customers.add(new Customer(id,name,adress));
    }
    public void displayCustomer(){
        for (Customer customer :customers) {
            System.out.println(customer);
        }
    }
    public int findIndexCustomer(String id){
        int index = -1;
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i).getId().equals(id)){
                index = i;
            }
        }
        return index;
    }
    public void editCustomer(){
        System.out.println("Nhập vào id cần sửa");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        int index = findIndexCustomer(id);
        if(index == -1){
            System.out.println("ID không đúng");
        }
        else {
            System.out.println("Sửa tên khách hàng: ");
            Scanner scanner1 = new Scanner(System.in);
            String name = scanner1.nextLine();
            customers.get(index).setName(name);
            System.out.println("Sửa địa chỉ khách hàng");
            String adress = scanner1.nextLine();
            customers.get(index).setAdress(adress);
            try {
                readWriteFileCustomer.writeFile(customers);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public ReadWriteFileCustomer getReadWriteFileCustomer() {
        return readWriteFileCustomer;
    }

    public void setReadWriteFileCustomer(ReadWriteFileCustomer readWriteFileCustomer) {
        this.readWriteFileCustomer = readWriteFileCustomer;
    }
}
