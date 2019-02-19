package com.mitrais.book_library_mgmt.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.mitrais.book_library_mgmt.model.Book;
import com.mitrais.book_library_mgmt.model.Shelf;
import com.mitrais.book_library_mgmt.repository.BookRepository;
import com.mitrais.book_library_mgmt.repository.ShelfRepository;
import com.mitrais.book_library_mgmt.service.dto.BookDTO;
import com.mitrais.book_library_mgmt.service.dto.BookShelfDTO;
import com.mitrais.book_library_mgmt.service.impl.BookServiceImpl;
import com.mitrais.book_library_mgmt.service.mapper.BookDTOMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ShelfRepository shelfRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Captor
    ArgumentCaptor captor;

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
    public void verifyFindByIsShelvedAndTitle(){
        String status = "shelved";
        String title = "xxx";

        bookService.findBooks(title,status);

        verify(bookRepository).findByIsShelvedAndTitle(anyBoolean(), eq(title));
    }


    @Test
    public void verifyFindByIsNotShelvedAndTitle(){
        String status = "not_shelved";
        String title = "xxx";

        bookService.findBooks(title,status);

        verify(bookRepository).findByIsShelvedAndTitle(anyBoolean(), eq(title));
    }

    @Test
    public void verifyFindByTitle(){
        String status = "";
        String title = "xxx";

        bookService.findBooks(title,status);

        verify(bookRepository).findByTitle(title);
    }

    @Test
    public void verifyFindByIsShelved(){
        String status = "shelved";
        String title = null;

        bookService.findBooks(title,status);

        verify(bookRepository).findByIsShelved(anyBoolean());
    }

    @Test
    public void verifyFindByIsNotShelved(){
        String status = "not_shelved";
        String title = null;

        bookService.findBooks(title,status);

        verify(bookRepository).findByIsShelved(anyBoolean());
    }

    @Test
    public void verifyFindAll(){
        String status = "";
        String title = null;

        bookService.findBooks(title,status);

        verify(bookRepository).findAll();
    }

    @Test
    public void verifyBookIntoShelfSuccessSave(){
        BookShelfDTO bookShelfDTO = new BookShelfDTO();
        bookShelfDTO.setBookId("bo001");
        bookShelfDTO.setShelfId("sh001");

        Book book = new Book();
        book.setId("bo001");
        book.setShelved(false);
        book.setAuthor("author");
        book.setIsbn("isbn");
        book.setTitle("title");

        Shelf shelf = new Shelf();
        shelf.setId("sh001");
        shelf.setCurrentCapacity(Long.valueOf(9));
        shelf.setMaxCapacity(Long.valueOf(10));

        when(bookRepository.findById(anyString())).thenReturn(
                Optional.of(book)
        );

        when(shelfRepository.findById(anyString())).thenReturn(
                Optional.of(shelf)
        );

        bookService.addBookIntoShelf(bookShelfDTO);

        verify(bookRepository).save(any(Book.class));


    }

    @Test
    public void addBookIntoShelfButBookIsNotPresent(){
        BookShelfDTO bookShelfDTO = new BookShelfDTO();
        bookShelfDTO.setBookId("bo001");
        bookShelfDTO.setShelfId("sh001");

        Shelf shelf = new Shelf();
        shelf.setId("sh001");
        shelf.setCurrentCapacity(Long.valueOf(9));
        shelf.setMaxCapacity(Long.valueOf(10));

        when(bookRepository.findById(anyString())).thenReturn(
                Optional.empty()
        );

        when(shelfRepository.findById(anyString())).thenReturn(
                Optional.of(shelf)
        );

        BookDTO bookDTO = bookService.addBookIntoShelf(bookShelfDTO);

        assertNull(bookDTO);
    }

    @Test
    public void addBookIntoShelfButShelfIsNotPresent(){
        BookShelfDTO bookShelfDTO = new BookShelfDTO();
        bookShelfDTO.setBookId("bo001");
        bookShelfDTO.setShelfId("sh001");

        Book book = new Book();
        book.setId("bo001");
        book.setShelved(false);
        book.setAuthor("author");
        book.setIsbn("isbn");
        book.setTitle("title");

        when(bookRepository.findById(anyString())).thenReturn(
                Optional.of(book)
        );

        when(shelfRepository.findById(anyString())).thenReturn(
                Optional.empty()
        );

        BookDTO bookDTO = bookService.addBookIntoShelf(bookShelfDTO);

        assertNull(bookDTO);
    }

    @Test
    public void verifyBookIntoShelfButShelvedIsTrue(){
        BookShelfDTO bookShelfDTO = new BookShelfDTO();
        bookShelfDTO.setBookId("bo001");
        bookShelfDTO.setShelfId("sh001");

        Book book = new Book();
        book.setId("bo001");
        book.setShelved(true);
        book.setAuthor("author");
        book.setIsbn("isbn");
        book.setTitle("title");

        Shelf shelf = new Shelf();
        shelf.setId("sh001");
        shelf.setCurrentCapacity(Long.valueOf(9));
        shelf.setMaxCapacity(Long.valueOf(10));

        when(bookRepository.findById(anyString())).thenReturn(
                Optional.of(book)
        );

        when(shelfRepository.findById(anyString())).thenReturn(
                Optional.of(shelf)
        );

        BookDTO bookDTO = bookService.addBookIntoShelf(bookShelfDTO);

        assertNull(bookDTO);

    }

    @Test
    public void verifyBookIntoShelfWithMaxCurrentCapacity(){
        BookShelfDTO bookShelfDTO = new BookShelfDTO();
        bookShelfDTO.setBookId("bo001");
        bookShelfDTO.setShelfId("sh001");

        Book book = new Book();
        book.setId("bo001");
        book.setShelved(false);
        book.setAuthor("author");
        book.setIsbn("isbn");
        book.setTitle("title");

        Shelf shelf = new Shelf();
        shelf.setId("sh001");
        shelf.setCurrentCapacity(Long.valueOf(10));
        shelf.setMaxCapacity(Long.valueOf(10));

        when(bookRepository.findById(anyString())).thenReturn(
                Optional.of(book)
        );

        when(shelfRepository.findById(anyString())).thenReturn(
                Optional.of(shelf)
        );

        BookDTO bookDTO = bookService.addBookIntoShelf(bookShelfDTO);

        assertNull(bookDTO);

    }

    @Test
    public void verifyRemoveBookFromShelfSuccessSave(){
        BookShelfDTO bookShelfDTO = new BookShelfDTO();
        bookShelfDTO.setBookId("bo001");
        bookShelfDTO.setShelfId("sh001");

        Book book = new Book();
        book.setId("bo001");
        book.setShelved(false);
        book.setAuthor("author");
        book.setIsbn("isbn");
        book.setTitle("title");

        Shelf shelf = new Shelf();
        shelf.setId("sh001");
        shelf.setCurrentCapacity(Long.valueOf(9));
        shelf.setMaxCapacity(Long.valueOf(10));

        book.setShelf(shelf);

        when(bookRepository.findBookByIdAndShelfId(bookShelfDTO.getBookId(),bookShelfDTO.getShelfId())).thenReturn(
                book
        );

        bookService.removeBookFromShelf(bookShelfDTO);

        verify(shelfRepository).save(any(Shelf.class));

        verify(bookRepository).save(any(Book.class));

    }

    @Test
    public void verifyRemoveBookFromShelfButBookIsNotPresent(){
        BookShelfDTO bookShelfDTO = new BookShelfDTO();
        bookShelfDTO.setBookId("bo001");
        bookShelfDTO.setShelfId("sh001");

        Book book = new Book();
        book.setId("bo001");
        book.setShelved(false);
        book.setAuthor("author");
        book.setIsbn("isbn");
        book.setTitle("title");

        Shelf shelf = new Shelf();
        shelf.setId("sh001");
        shelf.setCurrentCapacity(Long.valueOf(9));
        shelf.setMaxCapacity(Long.valueOf(10));

        book.setShelf(shelf);

        when(bookRepository.findBookByIdAndShelfId(bookShelfDTO.getBookId(),bookShelfDTO.getShelfId())).thenReturn(
                null
        );

        BookDTO bookDTO = bookService.removeBookFromShelf(bookShelfDTO);

        assertNull(bookDTO);

    }
}
