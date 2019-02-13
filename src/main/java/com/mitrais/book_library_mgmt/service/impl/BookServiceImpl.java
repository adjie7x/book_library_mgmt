package com.mitrais.book_library_mgmt.service.impl;

import com.mitrais.book_library_mgmt.model.Book;
import com.mitrais.book_library_mgmt.repository.BookRepository;
import com.mitrais.book_library_mgmt.service.BookService;
import com.mitrais.book_library_mgmt.service.dto.BookDTO;
import com.mitrais.book_library_mgmt.service.mapper.BookDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bookService")
public class BookServiceImpl implements BookService {


    @Autowired
    BookRepository bookRepository;

    @Override
    public BookDTO create(BookDTO bookDTO) throws Exception {

        Book book = BookDTOMapper.INSTANCE.bookDTOToBook(bookDTO);
        book = bookRepository.save(book);

        return BookDTOMapper.INSTANCE.bookToBookDTO(book);
    }
}
