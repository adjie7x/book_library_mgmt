package com.mitrais.book_library_mgmt.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.mitrais.book_library_mgmt.model.Book;
import com.mitrais.book_library_mgmt.repository.BookRepository;
import com.mitrais.book_library_mgmt.service.dto.BookDTO;
import com.mitrais.book_library_mgmt.service.impl.BookServiceImpl;
import com.mitrais.book_library_mgmt.service.mapper.BookDTOMapper;
import org.junit.Ignore;
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
    private BookServiceImpl bookService;

    @Test
//    @Ignore
    public void bookServiceCreateTest(){

//        Book expected = new Book();
//        expected.setId("dummy1");
//
//        when(bookRepository.save(any())).thenReturn(
//                expected
//        );

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("title");
        bookDTO.setIsbn("isbn");
        bookDTO.setAuthor("author");

        try {
            bookService.create(bookDTO);
        }catch (Exception e){
            e.printStackTrace();
        }


        verify(bookRepository).save(any(Book.class));



    }



    @Test
    @Ignore
    public void test(){
        assertTrue(true);
    }
}
