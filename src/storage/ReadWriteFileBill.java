package storage;

import model.Bill;
import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteFileBill {
    private static ReadWriteFileBill readWriteFileBill;
    private ReadWriteFileBill() {
    }
    public static ReadWriteFileBill getInstance(){
        if (readWriteFileBill == null){
            readWriteFileBill = new ReadWriteFileBill();
        }
        return readWriteFileBill;
    }

    public List<Bill> readFile() throws IOException, ClassNotFoundException {
        File file = new File("listBill.txt");
        if (!file.exists()){
            file.createNewFile();
        }
        if (file.length() >0){
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            List<Bill> list = (List<Bill>) object;
            objectInputStream.close();
            fileInputStream.close();
            return list;
        }
        else return new ArrayList<>();
    }
    public void writeFile(List<Bill> billList) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream("listBill.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(billList);
        objectOutputStream.close();
        fileOutputStream.close();
    }
}

