package com.burakejder.DTO;

import java.math.BigDecimal;

public class DtoOrderItem {

    private Long orderItemId;

    private DtoOrder order;

    private DtoProduct product;

    private Integer quantity;

    private BigDecimal price;
}
