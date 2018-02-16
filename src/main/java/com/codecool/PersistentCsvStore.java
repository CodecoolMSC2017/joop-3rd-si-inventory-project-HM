package com.codecool;

import com.codecool.CsvStore;

public class PersistentCsvStore extends CsvStore {

    @Override
    protected void storeProduct(Product product) {
        getAllProduct().add(product);
    }

}
