package com.vedantraut.bookwarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedantraut.bookwarm.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

}
