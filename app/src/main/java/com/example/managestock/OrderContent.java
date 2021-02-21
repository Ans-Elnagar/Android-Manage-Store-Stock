package com.example.managestock;

import java.util.ArrayList;
import java.util.List;

public class OrderContent {
    private static class ProductAndQuantity{
        private Product product;
        private int orderQuantity;
        ProductAndQuantity(Product product, int orderQuantity){
            this.product = product;
            this.orderQuantity = orderQuantity;
        }

        void setProduct(Product product) {
            this.product = product;
        }

        Product getProduct() {
            return product;
        }

        void setOrderQuantity(int quantity) {
            this.orderQuantity = quantity;
        }

        int getOrderQuantity() {
            return orderQuantity;
        }
    }
    List<ProductAndQuantity> list;
    long time;

    public OrderContent(){
        this.list = new ArrayList<ProductAndQuantity>();
    }
    public void setTime(long time){
        this.time = time;
    }
    public long getTime(){
        return this.time;
    }

    public void add(Product product, int orderQuantity){
        int temp = list.size();
        for(int i=0; i<temp; i++){
            if(list.get(i).getProduct().equals(product)){
                list.get(i).setOrderQuantity(orderQuantity);
                return;
            }
        }
        list.add(new ProductAndQuantity(product, orderQuantity));
    }

    public void remove(Product product){
        int temp = list.size();
        for(int i=0; i<temp; i++){
            if(list.get(i).getProduct().equals(product)){
                list.remove(i);
                return;
            }
        }
    }

    public void remove(int i){
        if(i<list.size()){
            list.remove(i);
        }
    }

    public int size(){
        return list.size();
    }

    public Product getProduct(int i){
        if(i<list.size())
            return list.get(i).getProduct();
        return null;
    }
    public int getQuantity(int i){
        if(i<list.size())
            return list.get(i).getOrderQuantity();
        return 0;
    }
    public int getQuantity(Product product){
        int temp = list.size();
        for(int i=0; i<temp; i++){
            if(list.get(i).getProduct().equals(product)){
                return list.get(i).getOrderQuantity();
            }
        }
        return 0;
    }
}
