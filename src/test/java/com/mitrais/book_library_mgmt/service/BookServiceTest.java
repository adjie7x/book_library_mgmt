package com.mitrais.book_library_mgmt.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.mitrais.book_library_mgmt.model.Book;
import com.mitrais.book_library_mgmt.repository.BookRepository;
import com.mitrais.book_library_mgmt.service.dto.BookDTO;
import com.mitrais.book_library_mgmt.service.mapper.BookDTOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void create(){

        Book expected = new Book();
        expected.setId("dummy1");

        when(bookRepository.save(any())).thenReturn(
                expected
        );

    }
}
