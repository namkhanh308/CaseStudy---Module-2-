package storage;

import Account.AccountCustomer;
import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteFileAccount implements Serializable {
    private static ReadWriteFileAccount readWriteFileAccount;
    private ReadWriteFileAccount() {
    }
    public static ReadWriteFileAccount getInstance(){
        if (readWriteFileAccount == null){
            readWriteFileAccount = new ReadWriteFileAccount();
        }
        return readWriteFileAccount;
    }

    public List<AccountCustomer> readFile() throws IOException, ClassNotFoundException {
        File file = new File("listAccount.txt");
        if (!file.exists()){
            file.createNewFile();
        }
        if (file.length() >0){
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            List<AccountCustomer> list = (List<AccountCustomer>) object;
            objectInputStream.close();
            fileInputStream.close();
            return list;
        }
        else return new ArrayList<>();
    }
    public void writeFile(List<AccountCustomer> accountCustomerList) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream("listAccount.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(accountCustomerList);
        objectOutputStream.close();
        fileOutputStream.close();
    }
}