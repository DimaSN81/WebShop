package org.campus.webshop.dao.jdbc.mapper;

import org.campus.webshop.entity.Product;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductRowMapperTest {
    @Test
    public void testMapRow() throws SQLException {
        ProductRowMapper productRowMapper = new ProductRowMapper();
        LocalDateTime localDateTime = LocalDateTime.of(2021, 10, 15, 15, 10, 22);
        Timestamp timestamp = Timestamp.valueOf(localDateTime);

        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getInt("id")).thenReturn(15);
        when(resultSetMock.getString("name")).thenReturn("Дрель");
        when(resultSetMock.getDouble("price")).thenReturn(1250.25);
        when(resultSetMock.getTimestamp("creationDate")).thenReturn(timestamp);

        Product actual = productRowMapper.mapRow(resultSetMock);

        assertEquals(15, actual.getId());
        assertEquals("Дрель", actual.getName());
        assertEquals(1250.25, actual.getPrice());
        assertEquals(localDateTime, actual.getCreationDate());
    }

}