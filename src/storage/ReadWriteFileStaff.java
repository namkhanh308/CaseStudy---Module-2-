package storage;

import model.Customer;
import model.Staff;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteFileStaff {
    private static ReadWriteFileStaff readWriteFileStaff;
    private ReadWriteFileStaff() {
    }
    public static ReadWriteFileStaff getInstance(){
        if (readWriteFileStaff == null){
            readWriteFileStaff = new ReadWriteFileStaff();
        }
        return readWriteFileStaff;
    }

    public List<Staff> readFile() throws IOException, ClassNotFoundException {
        File file = new File("listStaff.txt");
        if (!file.exists()){
            file.createNewFile();
        }
        if (file.length() >0){
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            List<Staff> list = (List<Staff>) object;
            objectInputStream.close();
            fileInputStream.close();
            return list;
        }
        else return new ArrayList<>();
    }
    public void writeFile(List<Staff> staffList) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream("listStaff.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(staffList);
        objectOutputStream.close();
        fileOutputStream.close();
    }
}
