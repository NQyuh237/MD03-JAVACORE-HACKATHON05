package RA.Run;

import RA.Config.InputMethods;
import RA.Controller.ProductController;
import RA.Model.Product;


import java.util.*;

public class BookManagement {
    static ProductController productController = new ProductController();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("**************************JAVA-HACKATHON-05-BASIC-MENU**************************\n" +
                    "1. Quản lý sách\n" +
                    "2. Thoát");
            System.out.println("Mời bạn chọn lựa từ 1 -3 cảm ơn!!!");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    productManagement();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Xin vui lòng nhập lại nhé !!!");
            }
        }
    }


    //    ==================================================================
    public static void productManagement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("********************BOOK-MANAGEMENT********************\n" +
                "1. Nhập số sách và nhập thông tin sách\n" +
                "2. Hiển thị thông tin các sách\n" +
                "3. Sắp xếp sách theo giá giảm dần\n" +
                "4. Xóa sách theo mã\n" +
                "5. Tìm kiếm sách theo tên sách\n" +
                "6. Thay đổi thông tin của sách theo mã sách\n" +
                "7. Quay lại");
        System.out.println("Xin mời lựa chọn từ 1  - 7");
        byte choice = InputMethods.getByte();
        switch (choice) {
            case 1:
                addProduct();
                break;
            case 2:
                showProduct();
                break;
            case 3:
                Collections.sort(productController.getProductAll(), Comparator.comparingDouble(Product::getProductPrice).reversed());
                break;
            case 4:
                deleteProduct();
                break;
            case 5:
                searchProductByName();
                break;
            case 6:
                updateProduct();
                break;
            case 7:
                System.out.println();
                break;
            default:
                System.out.println("Xin vui lòng nhập lại từ 1 - 7.");
        }
    }

     public static void addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số sách mới: ");
        int numProducts = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numProducts; i++) {
            System.out.println("Nhập thông tin cho sách thứ " + (i + 1));
            Product newProduct = new Product();
            newProduct.inputData();
            System.out.println("Chọn ID danh mục cần thêm: ");
            boolean flag = true;
            productController.save(newProduct);
        }
        System.out.println("Danh mục đã được thêm mới thành công.");
        showProduct();
    }

    public static void showProduct() {
        if (productController.getSize() == 0) {
            System.out.println("Không có thư mục  nào hết!!!");
            return;
        }
        for (Product product : productController.getProductAll()) {
            product.displayData();
        }
    }

    public static void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        showProduct();
        System.out.println("Nhập ID sách cần xóa: ");
        String id = scanner.nextLine();
        productController.delete(id);
    }

    public static Product searchProductByName() {
        List<Product> listSearch = new ArrayList<>();
        boolean check = false;
        System.out.println("Nhập tên sách muốn tìm kiếm: ");
        String name = InputMethods.getString();
        for (Product product : productController.getProductAll()) {
            if (product.getProductName().toLowerCase().contains(name.toLowerCase())) {
                product.displayData();
                break;
            }
        }
        System.out.println("Không tim thấy sách theo tác giả.");
        return null;
    }

    public static void updateProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã sách cần thay đổi thông tin: ");
        String productId = scanner.nextLine();

        Product product = productController.findById(productId);
        if (product != null) {
            System.out.println("Nhập thông tin mới cho sách: ");
            System.out.print("Tên sách: ");
            String newProductName = scanner.nextLine();

            System.out.print("Giá sách: ");
            double newProductPrice = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Mô tả sách: ");
            String newDescription = scanner.nextLine();

            System.out.print("Số lượng tồn kho: ");
            int newStock = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Trạng thái sách (true: bán, false: không bán): ");
            boolean newStatus = scanner.nextBoolean();
            scanner.nextLine();
            product.setProductName(newProductName);
            product.setProductPrice(newProductPrice);
            product.setDescription(newDescription);
            product.setStock(newStock);
            product.setStatus(newStatus);

            productController.save(product);
            System.out.println("Thay đổi thông tin sách thành công.");
        } else {
            System.out.println("Không tìm thấy sách với mã sách đã nhập.");
        }
    }
}
