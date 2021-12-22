package org.campus.webshop.dao;

import org.campus.webshop.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    List<Product> findAll();
}
