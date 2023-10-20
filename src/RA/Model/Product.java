package RA.Model;

import RA.Config.InputMethods;

import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;
    private double productPrice;
    private String description;
    private int stock;
    private boolean status;

    public Product() {
    }

    public Product(String productId, String productName, double productPrice, String description, int stock, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
        this.stock = stock;
        this.status = true;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Xin mời nhập ID sách bắt đầu bằng chữ P và thêm 4 ký tự số.");
        this.productId = InputMethods.getString();
        validateProductId(productId);
        System.out.println("Hãy nhập tên sách");
        this.productName = InputMethods.getString();
        System.out.println("Hãy nhập giá: ");
        this.productPrice = InputMethods.getDouble();
        System.out.println("Hãy nhập mô tả: ");
        this.description = InputMethods.getString();
        System.out.println("Hãy nhập số lượng sách trong kho: ");
        this.stock = InputMethods.getInteger();

    }

    private String validateProductId(String productId) {
        if (productId == null || !productId.matches("P\\d{4}")) {
            throw new IllegalArgumentException("Mã sách không hợp lệ. Mã sách phải bắt đầu bằng chữ P và theo sau là 4 chữ số.");
        }
        return productId;
    }

    public void displayData() {
        System.out.println("---------------------------------------------------");
        System.out.println("ID sách: " + productId
                + " Tên sách: " + productName
                + " Giá sách: " + productPrice
                + " Mô tả: " + description
                + " Kho: " + stock
                + " Trạng thái: " + (status ? "Không Bán" : " bán"));
    }

    @Override
    public String toString() {
        return "Product ID: " + productId +
                ", Product Name: " + productName +
                ", Price: " + productPrice +
                ", Description: " + description +
                ", Stock: " + stock +
                ", Status: " + (status ? "Bán" : "Không bán");
    }
}
