package controller;

import model.Customer;
import model.Product;
import model.ShoppingCart;
import storage.ReadWriteFileCustomer;
import storage.ReadWriteFileProduct;
import storage.ReadWriteFileShoppingCart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCartManager {
    List<ShoppingCart> shoppingCartList;
    ProductManagerment productList ;
    CustomerManager customerManager;
    ReadWriteFileProduct readWriteFileProduct = ReadWriteFileProduct.getInstance();
    ReadWriteFileShoppingCart readWriteFileShoppingCart = ReadWriteFileShoppingCart.getInstance();
    ReadWriteFileCustomer readWriteFileCustomer = ReadWriteFileCustomer.getInstance();
    int indexProduct = -1;
    public ShoppingCartManager() {
        try {
            this.shoppingCartList = readWriteFileShoppingCart.readFile();
            this.productList = new ProductManagerment();
            this.customerManager = new CustomerManager();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Product chooseProduct(String id){
        for (int i = 0; i < productList.getProducts().size(); i++) {
            if(productList.getProducts().get(i).getId().equals(id)){
                Product product1 = productList.getProducts().get(i);
                indexProduct = i;
                return product1;
            }
        }
        return null;
    }
    public Customer chooseCustomer(String id){
        int index;
        for (int i = 0; i < customerManager.getCustomers().size(); i++) {
            if(customerManager.getCustomers().get(i).getId().equals(id)){
                Customer customer = customerManager.getCustomers().get(i);
                index = i;
                System.out.println(index);
                return customer;
            }
        }
        return null;
    }
    public int findIndexShoppingCart(String idCus){
        int index = -1;
        for (int i = 0; i < shoppingCartList.size(); i++) {
            if(shoppingCartList.get(i).getCustomer().getId().equals(idCus)){
                index = i;
            }
        }
        return index;
    }

    public boolean checkCartExis(String id){
        for (ShoppingCart shoppingCart : shoppingCartList ) {
            if(shoppingCart.getCustomer().getId().equals(id)){
                return true;
            }
        }
        return false;
    }
    public boolean checkProductCartExis(String idProduct, ShoppingCart shoppingCart){
        for (Product product :  shoppingCart.getProducts()) {
            if(product.getId().equals(idProduct)){
                return true;
            }
        }
        return false;
    }
    public Product chooseProductExist(String idProductExist, ShoppingCart shoppingCart){
        for (Product product :shoppingCart.getProducts()) {
            if(product.getId().equals(idProductExist)){
                return product;
            }
        }
        return null;
    }

    public void addShoppingCart(String idCus){
        boolean check = checkCartExis(idCus);
        if(check){
            int indexShoppingCart = findIndexShoppingCart(idCus);
            System.out.println("Chức năng thêm mới sản phẩm vào giỏ hàng");
            Scanner scanner = new Scanner(System.in);
            productList.displayProduct();
            System.out.println("Nhập vào id sản phẩm sản phẩm cần chọn: ");
            String idProduct = scanner.nextLine();
            System.out.println("Nhập vào số lượng sản phẩm: ");
            int count = scanner.nextInt();
            boolean checkProductCartExis = checkProductCartExis(idProduct,shoppingCartList.get(indexShoppingCart));
            if(checkProductCartExis){
                Product productExist = chooseProductExist(idProduct,shoppingCartList.get(indexShoppingCart));
                productExist.setQuantity(productExist.getQuantity()+count);
                Product product = chooseProduct(idProduct);
                productList.getProducts().get(indexProduct).setQuantity(product.getQuantity()-count);
                try {
                    readWriteFileShoppingCart.writeFile(shoppingCartList);
                    readWriteFileProduct.writeFile(productList.getProducts());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                Product product = chooseProduct(idProduct);
                Product product1 = new Product(product.getId(),product.getName(),product.getType(),product.getSupplier(),count,product.getPrice());
                shoppingCartList.get(indexShoppingCart).getProducts().add(product1);
                try {
                    readWriteFileShoppingCart.writeFile(shoppingCartList);
                    readWriteFileProduct.writeFile(productList.getProducts());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            List<Product> productsCartList = new ArrayList<>();
            System.out.println("Chức năng thêm mới sản phẩm vào giỏ hàng");
            Scanner scanner = new Scanner(System.in);
            productList.displayProduct();
            System.out.println("Nhập vào id sản phẩm sản phẩm cần chọn: ");
            String idProduct = scanner.nextLine();
            System.out.println("Nhập vào số lượng sản phẩm: ");
            int count = scanner.nextInt();
            Product product = chooseProduct(idProduct);
            Product product1 = new Product(product.getId(),product.getName(),product.getType(),product.getSupplier(),count,product.getPrice());
            productsCartList.add(product1);
            productList.getProducts().get(indexProduct).setQuantity(product.getQuantity()-count);
            System.out.println("Nhập vào id của khách hàng");
            Scanner scanner1 = new Scanner(System.in);
//        String idCus = scanner1.nextLine();
//        customerManager.displayCustomer();
            Customer customer = chooseCustomer(idCus);
            String id = String.valueOf(shoppingCartList.size());
            shoppingCartList.add(new ShoppingCart(id,customer,productsCartList));
            try {
                readWriteFileShoppingCart.writeFile(shoppingCartList);
                readWriteFileProduct.writeFile(productList.getProducts());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

//    public void displayPaymentStatus(){
//        for (ShoppingCart shoppingCart:shoppingCartList) {
//            if(shoppingCart.paymentStatus == true){
//                System.out.println(shoppingCart);
//            }
//        }
//    }
    public int findShopingCart(String idCustomer){
        int index = -1;
        for (int i = 0; i < shoppingCartList.size(); i++) {
            if(shoppingCartList.get(i).getCustomer().getId().equals(idCustomer)){
                index = i;
            }
        }
        return index;
    }
    public ShoppingCart findShoppingCart(String idCustomer){
        for (ShoppingCart shoppingCart : shoppingCartList) {
            if(shoppingCart.getCustomer().getId().equals(idCustomer)){
                return shoppingCart;
            }
        }
        return null;
    }
    public void displayProductInCart(List<Product> products){
        for (Product product:products ) {
            System.out.println(product);
        }
    }
    public void displayProductInMyCart(String idCustomer){
        int indexShoppingCart = findIndexShoppingCart(idCustomer);
        if(shoppingCartList.get(indexShoppingCart).getProducts() == null){
            System.out.println("Giỏ hàng không có gì");
        }
        else {
            for (Product product : shoppingCartList.get(indexShoppingCart).getProducts()){
                System.out.println(product);
            }
        }

    }
    public Customer findCustomerInShoppingCard(String id){
        for (ShoppingCart shoppingCart:shoppingCartList ) {
            if(shoppingCart.getCustomer().getId().equals(id)){
                return shoppingCart.getCustomer();
            }
        }
        return null;
    }

//    public void addProductToShoppingCart(String idCustomer){
//        if(findShopingCart(idCustomer) != -1){
//            System.out.println("Bạn muốn chọn sản phẩm nào");
//            productList.displayProduct();
//            Scanner scanner = new Scanner(System.in);
//            String idProduct = scanner.nextLine();
//            Product product = addProduct(idProduct);
//            shoppingCartList.get(findShopingCart(idCustomer)).getProducts().add(product);
//            try {
//                readWriteFileShoppingCart.writeFile(shoppingCartList);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            finally {
//                System.out.println("Thêm thành công");
//            }
//        }
//        else {
//            System.out.println("Không tìn thấy id khách khách hàng nào như vậy");
//        }
//    }
//    public Product addProduct(String id){
//        for (Product product : productList.getProducts()) {
//            if(product.getId().equals(id)){
//                product.setQuantity(product.getQuantity()-1);
//                try {
//                    readWriteFileProduct.writeFile(productList.getProducts());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Product product1 = product;
//                product1.setQuantity(1);
//                return product1;
//            }
//        }
//        return null;
//    }
    public void editProductInShoppingCart(String idCustomer) {
        if (checkCartExis(idCustomer)) {
            int indexShoppingCart =  findIndexShoppingCart(idCustomer);
            System.out.println("Chức năng sửa giỏ hàng");
            int indexEditProduct = findIndexShoppingCart(idCustomer);
            System.out.println("Danh sách sản phẩm có trong giỏ hàng");
            displayProductInCart(shoppingCartList.get(indexEditProduct).getProducts());
            Scanner scanner = new Scanner(System.in);
            System.out.println("Chọn sản phẩm bạn cần sửa");
            String idProduct = scanner.nextLine();
            if(checkProductCartExis(idProduct,shoppingCartList.get(indexEditProduct))){
                Product productExist = chooseProductExist(idProduct,shoppingCartList.get(indexShoppingCart));
                System.out.println("Bạn muốn sửa thông tin gì");
                System.out.println("1. Nhập số lượng sản phẩm");
                System.out.println("2. Xóa sản phẩm");
                Scanner scanner3 = new Scanner(System.in);
                int choose = scanner3.nextInt();
                switch (choose){
                    case  1:
                        Scanner scanner1 = new Scanner(System.in);
                        System.out.println("Nhập vào số lượng cần sửa: ");
                        int quantity = scanner1.nextInt();
                        productExist.setQuantity(quantity);
                        try {
                            readWriteFileShoppingCart.writeFile(shoppingCartList);
                            readWriteFileProduct.writeFile(productList.getProducts());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        while (true){
                            System.out.println("Bạn muốn xóa sản phẩm này không Y/N");
                            Scanner scanner2 = new Scanner(System.in);
                            String choose2 = scanner2.nextLine();
                            if(choose2.equals("Y")){
                                shoppingCartList.get(indexShoppingCart).getProducts().remove(productExist);
                                System.out.println("Xóa thành công");
                                try {
                                    readWriteFileShoppingCart.writeFile(shoppingCartList);
                                    readWriteFileProduct.writeFile(productList.getProducts());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                            else if(choose2.equals("N")){
                                System.out.println("Xóa không thành công");
                                try {
                                    readWriteFileShoppingCart.writeFile(shoppingCartList);
                                    readWriteFileProduct.writeFile(productList.getProducts());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                        break;
                }

            }

        }
        else {
            System.out.println("Không tồn tại giỏ hàng");
        }
    }


    public void displayShoppingCart(){
        for (ShoppingCart shoppingCart : shoppingCartList ) {
            System.out.println(shoppingCart);
        }
    }

    public CustomerManager getCustomerManager() {
        return customerManager;
    }

    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    public List<ShoppingCart> getShoppingCartList() {
        return shoppingCartList;
    }

    public void setShoppingCartList(List<ShoppingCart> shoppingCartList) {
        this.shoppingCartList = shoppingCartList;
    }

    public ProductManagerment getProductList() {
        return productList;
    }

    public void setProductList(ProductManagerment productList) {
        this.productList = productList;
    }
}
