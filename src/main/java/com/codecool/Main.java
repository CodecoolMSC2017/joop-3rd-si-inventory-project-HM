package com.codecool;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //com.codecool.PersistentStore myPersistent = new com.codecool.PersistentStore();
        //com.codecool.StoreManager myStorage = new com.codecool.StoreManager();

        PersistentCsvStore pcsvs = new PersistentCsvStore();
        StoreManager store = new StoreManager();
        //myStorage.addStorage(myPersistent);
        //myStorage.addCDProduct("Heroin Diaries",5000,13);
        //myStorage.addBookProduct("On the road", 3490, 378);

        //System.out.println(myStorage.listProducts());
        //System.out.println(myStorage.getTotalProductPrice());
        store.addStorage(pcsvs);
        store.addCDProduct("Heroin Diaries",5000,14);
        store.addBookProduct("On the road", 3490, 378);
        System.out.println(store.listProducts());
        pcsvs.writeCSV("/home/mate009/Java/joop-3rd-si-inventory-project-HM/product.csv");
    }
}
