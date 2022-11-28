package com.week3.springboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.week3.springboot.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
