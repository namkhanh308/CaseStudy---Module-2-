package Account;

import java.io.Serializable;

public class AccountStaff implements Account, Serializable {
    String account;
    String passWord;

    public String getAccount() {
        return account;
    }

    public AccountStaff(String account, String passWord) {
        this.account = account;
        this.passWord = passWord;
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
    public void register(String id, String email, String password) {

    }
    @Override
    public void changePassWord(String newPassword) {
        this.passWord = newPassword;
    }

    @Override
    public String toString() {
        return "AccountStaff{" +
                "account='" + account + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
