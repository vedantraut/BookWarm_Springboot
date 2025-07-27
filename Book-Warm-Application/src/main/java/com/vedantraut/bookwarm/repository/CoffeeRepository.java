package com.vedantraut.bookwarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedantraut.bookwarm.entity.Coffee;
import com.vedantraut.bookwarm.entity.Orders;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

}
