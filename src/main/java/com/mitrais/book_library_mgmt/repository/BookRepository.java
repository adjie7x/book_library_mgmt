package com.mitrais.book_library_mgmt.repository;

import com.mitrais.book_library_mgmt.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,String> {
}
