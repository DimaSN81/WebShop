package org.campus.webshop.dao.jdbc;

import org.campus.webshop.dao.ProductDao;
import org.campus.webshop.dao.jdbc.mapper.ProductRowMapper;
import org.campus.webshop.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao extends JdbcProcessor implements ProductDao {
    private static final String FIND_ALL_SQL = "SELECT id, name, price, creationDate FROM Product";
    private static final ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();

    @Override
    public List<Product> findAll() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL);) {
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = PRODUCT_ROW_MAPPER.mapRow(resultSet);
                products.add(product);
            }
            return products;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public JdbcProductDao() {
        try {
            JdbcProcessor.checkCreatingTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
