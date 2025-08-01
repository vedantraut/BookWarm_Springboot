package com.vedantraut.bookwarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedantraut.bookwarm.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
