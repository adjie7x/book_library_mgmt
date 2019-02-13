package com.mitrais.book_library_mgmt.service.mapper;

import com.mitrais.book_library_mgmt.service.dto.ShelfDTO;
import com.mitrais.book_library_mgmt.model.Shelf;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//@Mapper(componentModel = "spring", uses = {})
@Mapper
public interface ShelfDTOMapper {

    ShelfDTOMapper INSTANCE = Mappers.getMapper(ShelfDTOMapper.class);

    ShelfDTO shelfToShelfDTO(Shelf shelf);

    Shelf shelfDTOToShelf(ShelfDTO shelfDTO);
}
