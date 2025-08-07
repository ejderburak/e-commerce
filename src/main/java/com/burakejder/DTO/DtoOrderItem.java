package com.burakejder.DTO;

import com.burakejder.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoOrderItem {

    private Long orderItemId;
    private  Long orderId;
    private Product productId;
    private Integer quantity;
    private BigDecimal price;
}
