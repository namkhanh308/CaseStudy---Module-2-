package controller;

import model.Product;
import model.Staff;
import model.Type;
import storage.ReadWriteFileProduct;
import storage.ReadWriteFileStaff;
import storage.ReadWriteFileType;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class ProductManagerment implements Serializable {
    StaffManagerment staffStaffManagerment;
    List<Product> products;
    List<Type> types;
    private ReadWriteFileStaff readWriteFileStaff = ReadWriteFileStaff.getInstance();
    private ReadWriteFileProduct readWriteFileProduct = ReadWriteFileProduct.getInstance();
    private ReadWriteFileType readWriteFileType = ReadWriteFileType.getInstance();

    public ProductManagerment() {
        try {
            staffStaffManagerment = new StaffManagerment();
            products = readWriteFileProduct.readFile();
            types = readWriteFileType.readFile();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ProductManagerment(List<Product> products, List<Type> types, ReadWriteFileProduct readWriteFileProduct, ReadWriteFileType readWriteFileType) {
        this.products = products;
        this.types = types;
        this.readWriteFileProduct = readWriteFileProduct;
        this.readWriteFileType = readWriteFileType;
    }

    public void displayProduct(){
        try {
            staffStaffManagerment = new StaffManagerment();
            products = readWriteFileProduct.readFile();
            types = readWriteFileType.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Product product : products ) {
            System.out.println(product);
        }
    }
    public void addProduct() throws IOException {
        System.out.println("Chức năng thêm sản phẩm");
        Scanner scanner = new Scanner(System.in);
        String id = String.valueOf(products.size());
        System.out.println("Nhập tên sản phẩm");
        String name = scanner.nextLine();
        System.out.println("Nhập nhà cung cấp sản phẩm");
        String supplier = scanner.nextLine();
        System.out.println("Nhập vào số lượng sản phẩm");
        int quantity = scanner.nextInt();
        System.out.println("Nhập vào giá sản phẩm sản phẩm");
        double price = scanner.nextDouble();
        System.out.println("Chọn loại sản phẩm ");
        displayType();
        int choose = -1;
        while (choose != 0){
            Scanner scanner1 = new Scanner(System.in);
            String idType = scanner1.nextLine();
            if(findType(idType) != null){
                System.out.println("Chọn thành công");
                products.add(new Product(id,name,findType(idType),supplier,quantity,price));
                readWriteFileProduct.writeFile(products);
                readWriteFileType.writeFile(types);
                choose = 0;
            }
            else {
                System.out.println("Type bạn chọn không đúng");
            }
        }

    }
    public void repairProduct() throws IOException {
//        System.out.println("Chức năng sửa sản phẩm");
//        displayProduct();
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Nhập vào id sản phẩm cần sửa");
//        String id = scanner.nextLine();
//        int index = -1;
//        for (int i = 0; i < products.size(); i++) {
//            System.out.println(products.get(i).getId().equals(id));
//            if(products.get(i).getId().equals(id)){
//                index = i;
//            }
//        }
//
//        products.remove(index);
//        readWriteFileProduct.writeFile(products);
    }

    public void addTypeProduct() throws IOException {
        System.out.println("Chức năng thêm loại sản phẩm");
        Scanner scanner = new Scanner(System.in);
        String id = String.valueOf(types.size());
        System.out.println("Nhập vào tên loại của sản phẩm:");
        String typeName = scanner.nextLine();
        System.out.println("Nhập vào mô tả của sản phẩm: ");
        String discription = scanner.nextLine();
        types.add(new Type(id,typeName,discription));
        readWriteFileType.writeFile(types);

    }
    public Type findType(String id){
        for (Type type : types ) {
            if(type.getId().equals(id)){
                return type;
            }
        }
        return null;
    }
    public int FindProductbyType(String id){
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getType().getId().equals(id)){
                return i;
            }
        }
        return -1;
    }
    public void displayType(){
        for (Type type:types ) {
            System.out.println(type);
        }
    }
    public void deleteType() throws IOException {
        displayType();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập vào id sản phẩm bạn muốn xóa");
        String id = scanner.nextLine();
        int index = -1;
        for (int i = 0; i < types.size(); i++) {
            if(types.get(i).getId().equals(id)){
                index = i;
            }
        }
        int vitri = FindProductbyType(types.get(index).getId());
        products.get(vitri).setType(null);
        types.remove(index);
        readWriteFileType.writeFile(types);
        readWriteFileProduct.writeFile(products);
    }
    public void deleteProduct() throws IOException {
        displayProduct();
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        int index = -1;
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId().equals(id)){
                index = i;
            }
        }
        products.remove(index);
        readWriteFileProduct.writeFile(products);
        readWriteFileType.writeFile(types);
    }
    public void addVoucherToType(){
        System.out.println("Chức năng thêm voucher");
        Scanner scanner = new Scanner(System.in);
        String idType = scanner.nextLine();
        Type type = findType(idType);
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Nhập vào giảm giá của loại sản phẩm");
        double discount = scanner1.nextDouble();
        type.setDiscount(discount);
        findProductInListCart(idType);
        try {
            readWriteFileProduct.writeFile(products);
            readWriteFileType.writeFile(types);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void findProductInListCart(String idType){
        System.out.println(findType(idType));
        for (Product product : products) {
            if(product.getType().getId().equals(idType)){
                product.setType(findType(idType));
            }
        }
        try {
            readWriteFileProduct.writeFile(products);
            readWriteFileType.writeFile(types);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public ReadWriteFileProduct getReadWriteFileProduct() {
        return readWriteFileProduct;
    }

    public void setReadWriteFileProduct(ReadWriteFileProduct readWriteFileProduct) {
        this.readWriteFileProduct = readWriteFileProduct;
    }

    public StaffManagerment getStaffStaffManagerment() {
        return staffStaffManagerment;
    }

    public void setStaffStaffManagerment(StaffManagerment staffStaffManagerment) {
        this.staffStaffManagerment = staffStaffManagerment;
    }

    public ReadWriteFileType getReadWriteFileType() {
        return readWriteFileType;
    }

    public void setReadWriteFileType(ReadWriteFileType readWriteFileType) {
        this.readWriteFileType = readWriteFileType;
    }
}
