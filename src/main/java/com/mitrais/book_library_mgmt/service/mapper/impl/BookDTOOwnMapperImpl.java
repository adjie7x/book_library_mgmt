package com.mitrais.book_library_mgmt.service.mapper.impl;

import com.mitrais.book_library_mgmt.model.Book;
import com.mitrais.book_library_mgmt.service.dto.BookDTO;
import com.mitrais.book_library_mgmt.service.mapper.BookDTOOwnMapper;
import org.springframework.stereotype.Service;

@Service("bookDTOOwnMapper")
public class BookDTOOwnMapperImpl implements BookDTOOwnMapper {

    @Override
    public BookDTO bookToBookDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();

        bookDTO.setId( book.getId() );
        bookDTO.setIsbn( book.getIsbn() );
        bookDTO.setTitle( book.getTitle() );
        bookDTO.setAuthor( book.getAuthor() );

        return bookDTO;
    }

    @Override
    public Book bookDTOToBook(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( bookDTO.getId() );
        book.setIsbn( bookDTO.getIsbn() );
        book.setTitle( bookDTO.getTitle() );
        book.setAuthor( bookDTO.getAuthor() );
        book.setShelf(shelfFromId(bookDTO.getShelfId()));

        return book;
    }
}
