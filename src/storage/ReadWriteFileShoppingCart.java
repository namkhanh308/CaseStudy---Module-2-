package storage;

import model.ShoppingCart;
import model.Type;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteFileShoppingCart {
    private static ReadWriteFileShoppingCart readWriteFileShoppingCart ;
    private ReadWriteFileShoppingCart() {
    }
    public static ReadWriteFileShoppingCart getInstance(){
        if (readWriteFileShoppingCart == null){
            readWriteFileShoppingCart = new ReadWriteFileShoppingCart();
        }
        return readWriteFileShoppingCart;
    }

    public List<ShoppingCart> readFile() throws IOException, ClassNotFoundException {
        File file = new File("listShoppingCart.txt");
        if (!file.exists()){
            file.createNewFile();
        }
        if (file.length() >0){
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            List<ShoppingCart> list = (List<ShoppingCart>) object;
            objectInputStream.close();
            fileInputStream.close();
            return list;
        }
        else return new ArrayList<>();
    }
    public void writeFile(List<ShoppingCart> shoppingCarts) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream("listShoppingCart.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(shoppingCarts);
        objectOutputStream.close();
        fileOutputStream.close();
    }
}
