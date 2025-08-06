package com.burakejder.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoProduct {

    private Long productId;

    private String ProductName;

    private Integer inventory;

    private BigDecimal price;

    private DtoCategory productCategory;

}
