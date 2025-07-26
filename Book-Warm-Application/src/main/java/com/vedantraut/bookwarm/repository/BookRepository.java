package com.vedantraut.bookwarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vedantraut.bookwarm.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
