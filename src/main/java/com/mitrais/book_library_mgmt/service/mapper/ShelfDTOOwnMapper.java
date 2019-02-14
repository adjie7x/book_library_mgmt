package com.mitrais.book_library_mgmt.service.mapper;

import com.mitrais.book_library_mgmt.model.Shelf;
import com.mitrais.book_library_mgmt.service.dto.ShelfDTO;

public interface ShelfDTOOwnMapper {

    ShelfDTO shelfToShelfDTO(Shelf shelf);

    Shelf shelfDTOToShelf(ShelfDTO shelfDTO);


}
