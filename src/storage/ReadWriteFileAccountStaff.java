package storage;

import Account.AccountCustomer;
import Account.AccountStaff;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteFileAccountStaff {
    private static ReadWriteFileAccountStaff readWriteFileAccountStaff;
    private ReadWriteFileAccountStaff() {
    }
    public static ReadWriteFileAccountStaff getInstance(){
        if (readWriteFileAccountStaff == null){
            readWriteFileAccountStaff = new ReadWriteFileAccountStaff();
        }
        return readWriteFileAccountStaff;
    }

    public List<AccountStaff> readFile() throws IOException, ClassNotFoundException {
        File file = new File("listAccountStaff.txt");
        if (!file.exists()){
            file.createNewFile();
        }
        if (file.length() >0){
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            List<AccountStaff> list = (List<AccountStaff>) object;
            objectInputStream.close();
            fileInputStream.close();
            return list;
        }
        else return new ArrayList<>();
    }
    public void writeFile(List<AccountStaff> accountStaffList) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream("listAccountStaff.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(accountStaffList);
        objectOutputStream.close();
        fileOutputStream.close();
    }
}

