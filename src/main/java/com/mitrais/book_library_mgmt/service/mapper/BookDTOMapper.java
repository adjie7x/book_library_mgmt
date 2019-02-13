package com.mitrais.book_library_mgmt.service.mapper;

import com.mitrais.book_library_mgmt.service.dto.BookDTO;
import com.mitrais.book_library_mgmt.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookDTOMapper {

    BookDTOMapper INSTANCE = Mappers.getMapper(BookDTOMapper.class);

//    @Mapping(source = "shelf.id",target = "shelfId")
    BookDTO bookToBookDTO(Book book);

//    @Mapping(source = "shelfId",target = "shelf.id")
    Book bookDTOToBook(BookDTO bookDTO);

}
