package com.codecool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class CsvStore implements StorageCapable {

    private List productList = new ArrayList();

    protected abstract void storeProduct(Product product);

    @Override
    public List<Product> getAllProduct(){
        return productList;
    }


    @Override
    public void storeCDProduct(String name, int price, int tracks){
        storeProduct(createProduct("cd", name, price, tracks));
    }

    @Override
    public void storeBookProduct(String name, int price, int pages){
        storeProduct(createProduct("book", name, price, pages));
    }

    protected Product createProduct(String type, String name, int price, int size) {
         Product product = null;

         if(type.toLowerCase().equals("cd")){
             product = new CDProduct(name, price, size);
         } else {
             product = new BookProduct(name, price, size);
         }
        return product;
    }


    public void writeCSV(String filename) {
        if (productList.size() != 0) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < productList.size(); i++) {
                    if (productList.get(i) instanceof CDProduct) {
                        CDProduct cd = (CDProduct) productList.get(i);
                        sb.append("name="+cd.getName()+",");
                        sb.append("price="+cd.getPrice()+",");
                        sb.append("tracks="+cd.getNumOfTracks()+"\n");
                    }
                    else{
                        BookProduct book = (BookProduct) productList.get(i);
                        sb.append("name="+book.getName()+",");
                        sb.append("price="+book.getPrice()+",");
                        sb.append("pages="+book.getNumOfPages()+"\n");
                    }
                }

                bw.write(sb.toString());
                bw.close();

            }catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

    }


    public void storeCsv(String filename) {
        writeCSV(filename);
    }

}
