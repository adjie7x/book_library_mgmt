package com.mitrais.book_library_mgmt.service.mapper;

import com.mitrais.book_library_mgmt.model.Book;
import com.mitrais.book_library_mgmt.model.Shelf;
import com.mitrais.book_library_mgmt.service.dto.BookDTO;

public interface BookDTOOwnMapper {

    BookDTO bookToBookDTO(Book book);

    Book bookDTOToBook(BookDTO bookDTO);

    default Shelf shelfFromId(String id) {
        if (id == null) {
            return null;
        }
        Shelf shelf = new Shelf();
        shelf.setId(id);
        return shelf;
    }

}
