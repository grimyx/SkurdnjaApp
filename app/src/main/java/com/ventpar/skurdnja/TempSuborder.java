package com.ventpar.skurdnja;

import com.ventpar.skurdnja.SkurdnjaDB.ProductDB.Product;

public class TempSuborder {
    private int mQuantity;
    private Product mProduct;

    public TempSuborder(int quantity, Product product){
        mQuantity = quantity;
        mProduct = product;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    public Product getProduct() {
        return mProduct;
    }

    public void setProduct(Product product) {
        mProduct = product;
    }
}
