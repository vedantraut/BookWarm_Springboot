package com.vedantraut.bookwarm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vedantraut.bookwarm.dtos.CoffeeDTO;
import com.vedantraut.bookwarm.entity.Coffee;
import com.vedantraut.bookwarm.services.CoffeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/coffee")
public class CoffeeController {
	
	@Autowired
	private CoffeeService coffeeservice;

	@GetMapping("/getAllCoffees")
	public ResponseEntity<List<Coffee>> getAllCoffees(){
		
		List<Coffee> fetchAllCoffee = coffeeservice.fetchAllCoffee();
		
		return ResponseEntity.ok(fetchAllCoffee);
	}
	
	@GetMapping("/getCoffee")
	public ResponseEntity<Coffee> getCoffeeById(@RequestParam Long id){
		
		Coffee coffee = coffeeservice.fetchCoffeeById(id);
		
		if(coffee == null) {
			System.out.println("Coffee at id - "+id+" not found...");
			return ResponseEntity.notFound().build();
		}
		
		System.out.println("Coffee at id - "+id+" having name - "+coffee.getName());
		
		return ResponseEntity.ok(coffee);
	}
	
	@PostMapping("/createCoffee")
	public ResponseEntity<Coffee> createCoffee(@RequestBody @Valid CoffeeDTO coffeedto){
		
		Coffee newCoffee = coffeeservice.saveCoffee(coffeedto);
		
		return ResponseEntity.ok(newCoffee);
	}
	
	@DeleteMapping("/deleteCoffee/{id}")
	public ResponseEntity<String> deleteCoffee(@PathVariable Long id) {
		
		System.out.println("Deleting coffee with id - "+id);
		
		String deleteCoffeeResponse = coffeeservice.deleteCoffee(id);
		
		if(deleteCoffeeResponse.equalsIgnoreCase("Deleted Successfully!")) {
			System.out.println("Deleted the coffee successfully!");
			
			return ResponseEntity.ok(deleteCoffeeResponse);
		} 
		else {
			return ResponseEntity.internalServerError().body(deleteCoffeeResponse);
		}
	}
	
	@PutMapping("/updateCoffee")
	public ResponseEntity<?> updateCoffee(@RequestBody CoffeeDTO coffeedto) {
		
		try {
			Coffee updatedCoffee = coffeeservice.updateCoffee(coffeedto);
			return ResponseEntity.ok(updatedCoffee);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
}
