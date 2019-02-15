package com.mitrais.book_library_mgmt.service;

import com.mitrais.book_library_mgmt.service.dto.BookDTO;
import com.mitrais.book_library_mgmt.service.dto.BookShelfDTO;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BookService {

    public BookDTO create(BookDTO bookDTO) throws DataAccessException;

    public List<BookDTO> findBooks(String title, String status);

    public BookDTO addBookIntoShelf(BookShelfDTO bookShelfDTO);

    public BookDTO removeBookFromShelf(BookShelfDTO bookShelfDTO);
}
