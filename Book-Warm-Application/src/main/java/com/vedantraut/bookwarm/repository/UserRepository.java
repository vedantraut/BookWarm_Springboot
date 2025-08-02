package com.vedantraut.bookwarm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedantraut.bookwarm.entity.Author;
import com.vedantraut.bookwarm.entity.Book;
import com.vedantraut.bookwarm.entity.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByEmail(String email);

}
