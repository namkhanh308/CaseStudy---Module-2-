package storage;

import model.Customer;
import model.Type;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteFileCustomer implements Serializable {
    private static ReadWriteFileCustomer readWriteFileCustomer;
    private ReadWriteFileCustomer() {
    }
    public static ReadWriteFileCustomer getInstance(){
        if (readWriteFileCustomer == null){
            readWriteFileCustomer = new ReadWriteFileCustomer();
        }
        return readWriteFileCustomer;
    }

    public List<Customer> readFile() throws IOException, ClassNotFoundException {
        File file = new File("listCustomer.txt");
        if (!file.exists()){
            file.createNewFile();
        }
        if (file.length() >0){
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            List<Customer> list = (List<Customer>) object;
            objectInputStream.close();
            fileInputStream.close();
            return list;
        }
        else return new ArrayList<>();
    }
    public void writeFile(List<Customer> customers) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream("listCustomer.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(customers);
        objectOutputStream.close();
        fileOutputStream.close();
    }
}
