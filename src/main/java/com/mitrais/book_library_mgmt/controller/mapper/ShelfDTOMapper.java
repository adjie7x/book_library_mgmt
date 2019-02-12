package com.mitrais.book_library_mgmt.controller.mapper;

import com.mitrais.book_library_mgmt.controller.dto.ShelfDTO;
import com.mitrais.book_library_mgmt.model.Shelf;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ShelfDTOMapper {

    ShelfDTO shelfToShelfDTO(Shelf shelf);
}
