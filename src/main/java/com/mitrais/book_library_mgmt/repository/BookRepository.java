package com.mitrais.book_library_mgmt.repository;

import com.mitrais.book_library_mgmt.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,String> {

    List<Book> findByTitle(String title);

    List<Book> findByIsShelvedAndTitle(boolean isShelved, String title);

    List<Book> findByIsShelved(boolean isShelved);

    @Query("SELECT b FROM Book b WHERE b.id = :id AND b.shelf.id = :shelfId")
    Optional<Book> findBookByIdAndShelfId(@Param("id") String id, @Param("shelfId") String shelfId);

}
