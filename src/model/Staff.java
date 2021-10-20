package model;

import Account.AccountStaff;

import java.io.Serializable;

public class Staff implements Serializable {
    private String id;
    private String name;
    private String old;
    private String email;
    private AccountStaff accountStaff;

    public Staff(String id, String name, String old, String email, AccountStaff accountStaff) {
        this.id = id;
        this.name = name;
        this.old = old;
        this.email = email;
        this.accountStaff = accountStaff;
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

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountStaff getAccountStaff() {
        return accountStaff;
    }

    public void setAccountStaff(AccountStaff accountStaff) {
        this.accountStaff = accountStaff;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", old='" + old + '\'' +
                ", adress='" + email + '\'' +
                ", accountStaff=" + accountStaff +
                '}';
    }
}
