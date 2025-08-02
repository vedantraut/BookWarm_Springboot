package com.vedantraut.bookwarm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedantraut.bookwarm.dtos.CoffeeDTO;
import com.vedantraut.bookwarm.entity.Coffee;
import com.vedantraut.bookwarm.exceptions.CoffeeNotFoundException;
import com.vedantraut.bookwarm.repository.CoffeeRepository;

import jakarta.validation.Valid;

@Service
public class CoffeeService {
	
	@Autowired
	CoffeeRepository coffeerepository;

	public List<Coffee> fetchAllCoffee() {
		return coffeerepository.findAll();
	}

	public Coffee saveCoffee(CoffeeDTO coffeedto) {
		
		System.out.println("Coffee to be saved - "+coffeedto);
		
		Coffee coffee = new Coffee();
		
		coffee.setName(coffeedto.getName());
		coffee.setCoffeeType(coffeedto.getCoffeeType());
		coffee.setPrice(coffeedto.getPrice());
		coffee.setDescription(coffeedto.getDescription());
		
		return coffeerepository.save(coffee);
		
	}

	public Coffee fetchCoffeeById(Long id) {
		Coffee coffee = coffeerepository.findById(id)
						.orElseThrow(() -> new CoffeeNotFoundException("Coffee not found"));
		return coffee;
	}

	public String deleteCoffee(Long id) {
		
		if(!coffeerepository.existsById(id)) {
			throw new CoffeeNotFoundException("Coffee not found");
		}
		
		
		try {
			coffeerepository.deleteById(id);
			return "Deleted Successfully!";
		} catch (Exception e) {
//			e.printStackTrace();
			
			System.out.println("Error While deleting coffee!");
			return "Deleted Failure!";
		}
	}

	public Coffee updateCoffee(CoffeeDTO coffeedto) {
		
		Coffee existingCoffee = coffeerepository.findById(coffeedto.getCoffeeId())
						.orElseThrow(()-> new CoffeeNotFoundException("Coffee Not Found"));
		
		existingCoffee.setName(coffeedto.getName());
		existingCoffee.setCoffeeType(coffeedto.getCoffeeType());
		existingCoffee.setPrice(coffeedto.getPrice());
		existingCoffee.setDescription(coffeedto.getDescription());
		
		return coffeerepository.save(existingCoffee);
		
	}

}
