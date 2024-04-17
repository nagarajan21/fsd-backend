package com.startup.fsdbackend.repository;

import com.startup.fsdbackend.model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<UserOrder,Long> {

}
