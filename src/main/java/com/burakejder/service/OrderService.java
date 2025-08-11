package com.burakejder.service;

import com.burakejder.DTO.DtoOrder;
import com.burakejder.DTO.DtoOrderStatusUpdate;
import com.burakejder.entities.Order;
import com.burakejder.entities.OrderItem;
import com.burakejder.entities.Product;
import com.burakejder.entities.User;
import com.burakejder.mapper.DtoMapper;
import com.burakejder.repository.OrderRepository;
import com.burakejder.repository.ProductRepository;
import com.burakejder.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Data
@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    // creates new order
    public DtoOrder createOrder(DtoOrder dtoOrder) {

        Long UserId = dtoOrder.getUser().getUserId();
        Optional<User> optional = userRepository.findById(UserId);

        User dbUser = optional.get();
        Order order = DtoMapper.toEntity(dtoOrder);

        for (OrderItem orderItem : order.getOrderItems()) {
            Long productId = orderItem.getProduct().getProductId(); // or getProductId() depending on your field name
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isPresent()) {
                orderItem.setProduct(productOptional.get()); // or setProductId() depending on your field name
                orderItem.setOrder(order); // Set the order reference
            }
        }

        order.setUser(dbUser);
        Order savedOrder = orderRepository.save(order);

        return DtoMapper.toDto(savedOrder);
    }

    // getting the order of a specific id
    public DtoOrder getOrderById(Long id){
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isEmpty()){return null;}

        return DtoMapper.toDto(optional.get());
    }

    // gets all the orders of a user
    public List<DtoOrder> getOrderByUserId(Long id){

        List<Order> orders = orderRepository.findByUser_UserId(id);
        List<DtoOrder> dtoOrders = new ArrayList<>();

        for(Order order  : orders){
            DtoOrder dtoOrder = DtoMapper.toDto(order);
            dtoOrders.add(dtoOrder);
        }
        return dtoOrders;
    }

    // updating the order status
    public DtoOrder updateOrder(Long id, DtoOrderStatusUpdate statusUpdate){

        Optional<Order> optional = orderRepository.findById(id);
        if(optional.isEmpty()){return null;}

        // chaning the order
        Order order = optional.get();
        order.setStatus(statusUpdate.getStatus());

        Order dbOrder = orderRepository.save(order);
        return DtoMapper.toDto(dbOrder);
    }

    // order cancellation
    public boolean deleteOrder(Long id){
        boolean exists = orderRepository.existsById(id);
        if(!exists){return false;}

        orderRepository.deleteById(id);
        return true;
    }




}