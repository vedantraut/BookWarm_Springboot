package com.vedantraut.bookwarm.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedantraut.bookwarm.dtos.OrderDTO;
import com.vedantraut.bookwarm.dtos.ResponseDTO;
import com.vedantraut.bookwarm.entity.Book;
import com.vedantraut.bookwarm.entity.Coffee;
import com.vedantraut.bookwarm.entity.Orders;
import com.vedantraut.bookwarm.exceptions.BookNotFoundException;
import com.vedantraut.bookwarm.exceptions.CoffeeNotFoundException;
import com.vedantraut.bookwarm.repository.AuthorRepository;
import com.vedantraut.bookwarm.repository.BookRepository;
import com.vedantraut.bookwarm.repository.CoffeeRepository;
import com.vedantraut.bookwarm.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private BookRepository bookrepository;
	
	@Autowired
	private AuthorRepository authorrepository;
	
	@Autowired
	private CoffeeRepository coffeerepository;
	@Autowired
	private OrderRepository orderrepository;

	public ResponseDTO placeOrder(OrderDTO orderdto) {
		
		try {
			Book book = bookrepository.findById(orderdto.getBookId())
						.orElseThrow(()-> new BookNotFoundException("Book Not Found with id - "+orderdto.getBookId()));
			
			Coffee coffee = coffeerepository.findById(orderdto.getCoffeeId())
					.orElseThrow(()-> new CoffeeNotFoundException("Coffee Not Found with id - "+orderdto.getCoffeeId()));
			
			Orders order = new Orders();
			
			order.setBook(book);
			order.setCoffee(coffee);
			order.setTotalPrice(book.getPrice() + coffee.getPrice());
			order.setOrderTime(LocalDateTime.now());
			
			orderrepository.save(order);
			
//			ResponseDTO responsedto = new ResponseDTO();
//			responsedto.setMessage("Order Placed Successfully!");
			
			return new ResponseDTO(order.getOrderId(), "SUCCESS", "Order placed successfully!");
		} catch (BookNotFoundException e) {
//			e.printStackTrace();
			return new ResponseDTO(null, "FAILURE", e.getMessage());
		} catch (CoffeeNotFoundException e) {
//			e.printStackTrace();
			return new ResponseDTO(null, "FAILURE", e.getMessage());
		} catch (Exception e) {
//			e.printStackTrace();
			return new ResponseDTO(null, "FAILURE", e.getMessage());
		}

		
	}
	
}
