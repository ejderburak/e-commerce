package com.burakejder.controller;

import com.burakejder.DTO.DtoOrder;
import com.burakejder.DTO.DtoOrderStatusUpdate;
import com.burakejder.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    // POST - /api/v1/orders - lists all the orders
    @PostMapping
    public DtoOrder createOrder(@RequestBody DtoOrder dtoOrder){
        return orderService.createOrder(dtoOrder);
    }

    //GET - /api/v1/orders/{id} - get product by id
    @GetMapping("/{id}")
    public DtoOrder findById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    // GET - /api/v1/orders/user/{userId}
    @GetMapping("/user/{userId}")
    public List<DtoOrder> getOrderByUserId(@PathVariable Long userId) {
        return orderService.getOrderByUserId(userId);
    }

    // UPDATE STATUS -/api/v1/orders/{id}/status  - update order status
    @PutMapping("/{id}/status")
    public DtoOrder updateOrderStatus(@PathVariable Long id,
                                      @RequestBody DtoOrderStatusUpdate dtoOrderStatusUpdate){

        return orderService.updateOrder(id, dtoOrderStatusUpdate);
    }

    // DELETE ORDER - /api/v1/orders/{id} - deleting order
    @DeleteMapping("/{id}")
    public boolean deleteOrder(@PathVariable Long id){
        return orderService.deleteOrder(id);
    }


}
