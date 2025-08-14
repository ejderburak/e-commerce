package com.burakejder.DTO;

import com.burakejder.entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoOrder {

    private Long orderId;
    private Date orderDate;
    private BigDecimal totalPrice;
    private Integer quantity;
    private DtoUser user;
    private String status;
    private List<DtoOrderItem> orderItems = new ArrayList<>();
}
