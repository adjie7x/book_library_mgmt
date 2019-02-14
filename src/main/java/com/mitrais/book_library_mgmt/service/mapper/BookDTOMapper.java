package com.mitrais.book_library_mgmt.service.mapper;

import com.mitrais.book_library_mgmt.model.Shelf;
import com.mitrais.book_library_mgmt.service.dto.BookDTO;
import com.mitrais.book_library_mgmt.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookDTOMapper {

    BookDTOMapper INSTANCE = Mappers.getMapper(BookDTOMapper.class);

    @Mapping(source = "shelf.id",target = "shelfId")
    BookDTO bookToBookDTO(Book book);

    @Mapping(source = "shelfId",target = "shelf.id")
    Book bookDTOToBook(BookDTO bookDTO);

    List<BookDTO> booksToBookDTOs(List<Book> books);

    default Shelf ShelfFromId(String id) {
        if (id == null) {
            return null;
        }
        Shelf shelf = new Shelf();
        shelf.setId(id);
        return shelf;
    }

}
