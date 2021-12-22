package org.campus.webshop.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Builder

public class Product {
    private int id;
    private String name;
    private Double price;
    private Timestamp creationDate;

}
