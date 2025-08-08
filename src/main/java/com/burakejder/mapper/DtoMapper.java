package com.burakejder.mapper;

import com.burakejder.DTO.*;
import com.burakejder.entities.*;
import org.springframework.beans.BeanUtils;

// service katmanında dtoları kullanırken her defasında uzun uzun yazmak yerine hali hazırda bir mapper class
public class DtoMapper {

    // category  -> dtoCategory mapper
    public static DtoCategory toDto(Category entity){
        if(entity == null){
            return null;
        }
        return new DtoCategory(
                entity.getCategoryId(),
                entity.getCategoryName());
    }

    // DtoCategory -> category mapping
    public static Category toEntity(DtoCategory dto){
        if(dto == null){ return null; }

        Category entity = new Category();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    // product -> dtoProduct mapping
    public static DtoProduct toDto(Product entity){

        if(entity == null){ return null; }

        DtoProduct dto = new DtoProduct();
        BeanUtils.copyProperties(entity, dto);
        dto.setCategory(toDto(entity.getProductCategory()));

        return dto;
    }

    // dtoProduct -> product
    public static Product toEntity(DtoProduct dto){
        if(dto == null){ return null; }

        Product entity = new Product();
        BeanUtils.copyProperties(dto, entity);
        entity.setProductCategory(toEntity(dto.getCategory()));
        return entity;
    }

    // user -> DtoUser
    public static DtoUser toDto(User entity){
        if(entity == null){ return null; }

        DtoUser dto = new DtoUser();
        BeanUtils.copyProperties(entity, dto);

        return dto;
    }

    public static User toEntity(DtoUser dto){
        if(dto == null){ return null; }

        User entity = new User();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    // order -> dtoOrder
    public static DtoOrder toDto(Order entity){
        if(entity == null){ return null; }

        DtoOrder dto = new DtoOrder();
        BeanUtils.copyProperties(entity, dto);
        dto.setUser(toDto(entity.getUser()));

        return dto;
    }

    // dtoOrder -> order
    public static Order toEntity(DtoOrder dto){
        if(dto == null){ return null; }

        Order entity = new Order();
        BeanUtils.copyProperties(dto, entity);
        entity.setUser(toEntity(dto.getUser()));
        return entity;
    }

    // orderItem -> dtoOrderItem
    public static DtoOrderItem toDto(OrderItem entity){
        if(entity == null){
            return null;
        }

        DtoOrderItem dto = new DtoOrderItem();
        BeanUtils.copyProperties(entity, dto);

        dto.setOrder(toDto(entity.getOrderId()));
        dto.setProduct(toDto(entity.getProductId()));

        return dto;
    }

    public static OrderItem toEntity(DtoOrderItem dto){
        if (dto == null){ return null; }
        OrderItem entity = new OrderItem();

        BeanUtils.copyProperties(dto, entity);

        // fields that can not be copied
        entity.setOrderId(toEntity(dto.getOrder()));
        entity.setProductId(toEntity(dto.getProduct()));

        return entity;
    }

}
