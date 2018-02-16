package com.codecool;

import com.codecool.Product;

public class PersistentStore extends Store {

    @Override
    public void storeProduct(Product product) {
        productList.add(product);
    }

}

