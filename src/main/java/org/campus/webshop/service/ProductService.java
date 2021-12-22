package org.campus.webshop.service;

import org.campus.webshop.dao.ProductDao;
import org.campus.webshop.entity.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDao productDao;

    public ProductService(ProductDao productDao){
        this.productDao = productDao;
    }

    public List<Product> findAll(){
        List<Product> products = productDao.findAll();

        return products;
    }
}
