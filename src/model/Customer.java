package model;

import Account.AccountCustomer;

import java.io.Serializable;

public class Customer implements Serializable {
    private AccountCustomer accountCustomer;
    private String id;
    private String name;
    private String adress;
    private String email;

    public Customer() {
    }

    public Customer(AccountCustomer accountCustomer, String id, String name, String adress, String email) {
        this.accountCustomer = accountCustomer;
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.email = email;
    }

    public Customer(String id, String name, String adress) {
        this.id = id;
        this.name = name;
        this.adress = adress;
    }

    public Customer(AccountCustomer accountCustomer, String name, String adress) {
        this.accountCustomer = accountCustomer;
        this.name = name;
        this.adress = adress;
    }

    public Customer(AccountCustomer accountCustomer, String id, String name, String adress) {
        this.accountCustomer = accountCustomer;
        this.id = id;
        this.name = name;
        this.adress = adress;
    }

    public AccountCustomer getAccountCustomer() {
        return accountCustomer;
    }

    public void setAccountCustomer(AccountCustomer accountCustomer) {
        this.accountCustomer = accountCustomer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "accountCustomer=" + accountCustomer +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
