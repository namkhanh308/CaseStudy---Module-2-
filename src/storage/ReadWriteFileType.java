package storage;

import model.Product;
import model.Type;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteFileType implements Serializable {
    private static ReadWriteFileType readWriteFileType;
    private ReadWriteFileType() {
    }
    public static ReadWriteFileType getInstance(){
        if (readWriteFileType == null){
            readWriteFileType = new ReadWriteFileType();
        }
        return readWriteFileType;
    }

    public List<Type> readFile() throws IOException, ClassNotFoundException {
        File file = new File("listType.txt");
        if (!file.exists()){
            file.createNewFile();
        }
        if (file.length() >0){
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            List<Type> list = (List<Type>) object;
            objectInputStream.close();
            fileInputStream.close();
            return list;
        }
        else return new ArrayList<>();
    }
    public void writeFile(List<Type> types) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream("listType.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(types);
        objectOutputStream.close();
        fileOutputStream.close();
    }
}
