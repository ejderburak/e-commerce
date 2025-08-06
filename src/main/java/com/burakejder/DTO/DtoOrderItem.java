package com.burakejder.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoOrderItem {

    private Long orderItemId;

    private DtoOrder order;

    private DtoProduct product;

    private Integer quantity;

    private BigDecimal price;
}
