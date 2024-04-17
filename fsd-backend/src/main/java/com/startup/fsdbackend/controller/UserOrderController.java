package com.startup.fsdbackend.controller;

import com.startup.fsdbackend.exception.UserNotFoundException;
import com.startup.fsdbackend.model.UserOrder;
import com.startup.fsdbackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserOrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/oms")
    UserOrder newOrder(@RequestBody UserOrder newOrder){
        return orderRepository.save(newOrder);
    }

    @GetMapping("/oms/all")
    List<UserOrder> getAllOrders(){
        return orderRepository.findAll();
    }

    @GetMapping("/oms/{id}")
    UserOrder getUserOrderById(@PathVariable Long id){
        return orderRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
    }

    @PutMapping("/oms/{id}")
    UserOrder updateUserOrderById(@RequestBody UserOrder order,@PathVariable Long id){
        return orderRepository.findById(id)
                .map(userOrder -> {
                    userOrder.setOrderDate(order.getOrderDate());
                    userOrder.setAmount(order.getAmount());
                    userOrder.setDescription(order.getDescription());
                    userOrder.setStatus(order.getStatus());
                    return orderRepository.save(userOrder);
                }).orElseThrow(()-> new UserNotFoundException(id));
    }

    @DeleteMapping("/oms/{id}")
    String deleteUserOrderById(@PathVariable Long id){
        if(!orderRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        orderRepository.deleteById(id);
        return "The user order with id "+id+" has been deleted successfully";
    }
}
