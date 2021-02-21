package com.example.managestock;

import androidx.annotation.Nullable;

public class Product {
    private String name;
    private String code;
    private String category;
    private int quantity;
    private String description;
    private float buyingPrice;
    private float sellingPrice;
    private int piecesInPackage;

    public Product(){
        this.name = "";
        this.code = "";
        this.category = "";
        this.quantity = 0;
        this.description = "";
        this.buyingPrice = 0;
        this.sellingPrice = 0;
        this.piecesInPackage = 1;
    }

    public Product(String name, String code, String category, int quantity, String description, float buyingPrice, float sellingPrice, int piecesInPackage){
        this.name = name;
        this.code = code;
        this.category = category;
        this.quantity = quantity;
        this.description = description;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.piecesInPackage = piecesInPackage;
    }

    public int getPiecesInPackage() {
        return piecesInPackage;
    }

    public void setPiecesInPackage(int piecesInPackage) {
        this.piecesInPackage = piecesInPackage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(float buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof Product){
            if( code.equals( ((Product) obj).getCode()))
                return true;
        }
        return false;
    }
}
