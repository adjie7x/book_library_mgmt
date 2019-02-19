package com.mitrais.book_library_mgmt.service.impl;

import com.mitrais.book_library_mgmt.model.Book;
import com.mitrais.book_library_mgmt.model.Shelf;
import com.mitrais.book_library_mgmt.repository.BookRepository;
import com.mitrais.book_library_mgmt.repository.ShelfRepository;
import com.mitrais.book_library_mgmt.service.BookService;
import com.mitrais.book_library_mgmt.service.dto.BookDTO;
import com.mitrais.book_library_mgmt.service.dto.BookShelfDTO;
import com.mitrais.book_library_mgmt.service.mapper.BookDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("bookService")
public class BookServiceImpl implements BookService {


    @Autowired
    BookRepository bookRepository;

    @Autowired
    ShelfRepository shelfRepository;

    @Override
    public BookDTO create(BookDTO bookDTO) throws DataAccessException {

        Book book = BookDTOMapper.INSTANCE.bookDTOToBook(bookDTO);
        book = bookRepository.save(book);

        return BookDTOMapper.INSTANCE.bookToBookDTO(book);
    }

    @Override
    public List<BookDTO> findBooks(String title, String status) {

        List<Book> books = new ArrayList<>();

        if(!"".equals(title) && title != null){
            if("shelved".equals(status) || "not_shelved".equals(status)){
                books = bookRepository.findByIsShelvedAndTitle("shelved".equals(status)?true:false,title);
            }else{
                books = bookRepository.findByTitle(title);
            }
        }else{
            if("shelved".equals(status) || "not_shelved".equals(status)){
                books = bookRepository.findByIsShelved("shelved".equals(status)?true:false);
            }else{
                books = bookRepository.findAll();
            }
        }

        return BookDTOMapper.INSTANCE.booksToBookDTOs(books);
    }

    @Override
    @Transactional
    public BookDTO addBookIntoShelf(BookShelfDTO bookShelfDTO) {

        Optional<Book> book = bookRepository.findById(bookShelfDTO.getBookId());
        Optional<Shelf> shelf = shelfRepository.findById(bookShelfDTO.getShelfId());

        if(book.isPresent() && shelf.isPresent()){
            Book bookOfShelf = book.get();
            Shelf shelfOfBook = shelf.get();

            if (!bookOfShelf.isShelved() && shelfOfBook.getCurrentCapacity() < shelfOfBook.getMaxCapacity()){
                bookOfShelf.setShelved(true);

                shelfOfBook.setCurrentCapacity(shelfOfBook.getCurrentCapacity()+1);

                bookOfShelf.setShelf(shelfOfBook);

                bookOfShelf = bookRepository.save(bookOfShelf);

                return BookDTOMapper.INSTANCE.bookToBookDTO(bookOfShelf);
            }
        }

        return null;
    }

    @Override
    @Transactional
    public BookDTO removeBookFromShelf(BookShelfDTO bookShelfDTO) {

        Optional<Book> bookJ = bookRepository.findBookByIdAndShelfId(bookShelfDTO.getBookId(), bookShelfDTO.getShelfId());

        if (bookJ.isPresent()){

            Book bookOfShelf = bookJ.get();
            Shelf shelfOfBook = bookOfShelf.getShelf();

            shelfOfBook.setCurrentCapacity(shelfOfBook.getCurrentCapacity()-1);
            shelfOfBook = shelfRepository.save(shelfOfBook);

            bookOfShelf.setShelved(false);
            bookOfShelf.setShelf(null);
            bookOfShelf = bookRepository.save(bookOfShelf);

            return BookDTOMapper.INSTANCE.bookToBookDTO(bookOfShelf);
        }

        return null;
    }
}
