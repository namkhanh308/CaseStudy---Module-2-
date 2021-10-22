package Account;

import java.io.Serializable;

public class AccountCustomer implements Serializable, Account {
    private String account;
    private String passWord;

    public AccountCustomer(String account, String passWord) {
        this.account = account;
        this.passWord = passWord;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "AccountCustomer{" +
                "account='" + account + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

    @Override
    public void register(String id, String email, String password) {

    }

    @Override
    public void changePassWord(String newPassword) {

    }
}
