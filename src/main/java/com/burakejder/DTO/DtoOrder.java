package com.burakejder.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoOrder {

    private Long orderId;

    private Date orderDate;

    private BigDecimal totalPrice;

    private Integer quantity;

    private DtoUser user;
}
