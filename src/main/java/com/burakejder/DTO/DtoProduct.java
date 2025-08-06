package com.burakejder.DTO;

import com.burakejder.entities.Category;

import java.math.BigDecimal;

public class DtoProduct {

    private Long productId;

    private String ProductName;

    private Integer inventory;

    private BigDecimal price;

    private DtoCategory productCategory;

}
