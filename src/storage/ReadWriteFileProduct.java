package storage;

import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteFileProduct implements Serializable {
    private static ReadWriteFileProduct readWriteFileProduct;
    private ReadWriteFileProduct() {
    }
    public static ReadWriteFileProduct getInstance(){
        if (readWriteFileProduct == null){
            readWriteFileProduct = new ReadWriteFileProduct();
        }
        return readWriteFileProduct;
    }

    public List<Product> readFile() throws IOException, ClassNotFoundException {
        File file = new File("listProduct.txt");
        if (!file.exists()){
            file.createNewFile();
        }
        if (file.length() >0){
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            List<Product> list = (List<Product>) object;
            objectInputStream.close();
            fileInputStream.close();
            return list;
        }
        else return new ArrayList<>();
    }
    public void writeFile(List<Product> products) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream("listProduct.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(products);
        objectOutputStream.close();
        fileOutputStream.close();
    }
}
