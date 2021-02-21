package com.example.managestock;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileTools {

    public static final String PRODUCTS_FILE_NAME = "products.json";
    public static final String HISTORY_FILE_NAME = "history.json";
    public static final String CATEGORIES_FILE_NAME = "categories.json";

    private static FileTools instance = new FileTools();

    private Gson gson;
    private FileTools(){ gson = new Gson();}
    public static FileTools getInstance(){return instance;}

    public void writeFile(String fileName, Object data){
        File textFile = new File(MainActivity.externalPath + "/" + fileName);
        try{
            PrintWriter writer = new PrintWriter(textFile);
            String productsJ = new Gson().toJson(data);
            Log.d("ts", productsJ);
            writer.write(productsJ);
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public String readFile(String fileName){
        StringBuilder content = new StringBuilder();
        String line;
        try{
            FileReader textFile = new FileReader(MainActivity.externalPath + "/" + fileName);
            BufferedReader reader = new BufferedReader(textFile);
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();
        }catch (FileNotFoundException e){
            writeFile(fileName, new ArrayList<String>());
            return readFile(fileName);
        }catch (IOException e){
            e.printStackTrace();
        }
        return content.toString();
    }

    Product jsonToProduct(JSONObject jProduct) throws JSONException {
        return new Product( jProduct.getString("name"),
                jProduct.getString("code"),
                jProduct.getString("category"),
                jProduct.getInt("quantity"),
                jProduct.getString("description"),
                (float)jProduct.getDouble("buyingPrice"),
                (float)jProduct.getDouble("sellingPrice"),
                jProduct.getInt("piecesInPackage"));
    }
    public List<Product> loadAllProducts(Context context){
        List<Product> list = new ArrayList<>();
        String productsJson = readFile(PRODUCTS_FILE_NAME);
        try {
            JSONArray productsJsonArray = new JSONArray(productsJson);
            int length = productsJsonArray.length();
            for(int i=0; i<length; i++){
                list.add(jsonToProduct(productsJsonArray.getJSONObject(length - i - 1)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void saveAllProducts(Context context){
        writeFile(PRODUCTS_FILE_NAME, MainActivity.allProducts);
    }

    OrderContent jsonToOrderContent(JSONObject jOrderContent){
        return gson.fromJson(jOrderContent.toString(), OrderContent.class);
    }

    public List<OrderContent> loadHistory(Context context) {
        List<OrderContent> list = new ArrayList<>();
        String historyJson = readFile(HISTORY_FILE_NAME);
        try {
            JSONArray historyArray = new JSONArray(historyJson);
            int length = historyArray.length();
            for(int i=length-1; i >= 0; i--){
                list.add(jsonToOrderContent(historyArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void saveHistory(Context context){
        writeFile(HISTORY_FILE_NAME, MainActivity.history);
    }

    public List<String> loadCategories(Context context){
        List<String> list = new ArrayList<>();
        String categoriesJson = readFile(CATEGORIES_FILE_NAME);
        try {
            JSONArray categoriesJsonArray = new JSONArray(categoriesJson);
            int length = categoriesJsonArray.length();
            for(int i=0; i<length; i++){
                list.add(categoriesJsonArray.getString(length - i - 1));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void saveCategories(Context context){
        writeFile(CATEGORIES_FILE_NAME, MainActivity.categories);
    }

    public void loadAppData(Context context){
        MainActivity.allProducts = loadAllProducts(context);
        MainActivity.history = loadHistory(context);
        MainActivity.categories = loadCategories(context);
    }
}
