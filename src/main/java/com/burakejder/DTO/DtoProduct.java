package com.burakejder.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoProduct {

    private Long productId;
    private String productName;
    private Integer inventory;
    private BigDecimal price;

    @NotNull(message = "category is required")
    private DtoCategory category;

}
