package controller;

import model.Staff;
import storage.ReadWriteFileStaff;

import java.io.IOException;
import java.util.List;

public class StaffManagerment {
    private List<Staff> staffList;
    private ReadWriteFileStaff readWriteFileStaff = ReadWriteFileStaff.getInstance();

    public StaffManagerment() {
        try {
            staffList = readWriteFileStaff.readFile();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }
    //    public int getTotalPrice(){
//        int total = 0;
//        for (Order order : orders) {
//            total += order.getTotalPrice();
//        }
//        return total;
//    }
}
