package com.burakejder.DTO;

import java.math.BigDecimal;
import java.util.Date;

public class DtoOrder {

    private Long orderId;

    private Date orderDate;

    private BigDecimal totalPrice;

    private Integer quantity;

    private DtoUser user;
}
