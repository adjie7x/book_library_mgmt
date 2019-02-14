package com.mitrais.book_library_mgmt.service.mapper.impl;

import com.mitrais.book_library_mgmt.model.Shelf;
import com.mitrais.book_library_mgmt.service.dto.ShelfDTO;
import com.mitrais.book_library_mgmt.service.mapper.ShelfDTOOwnMapper;
import org.springframework.stereotype.Service;

@Service("shelfDTOOwnMapper")
public class ShelfDTOOwnMapperImpl implements ShelfDTOOwnMapper {

    @Override
    public ShelfDTO shelfToShelfDTO(Shelf shelf) {
        if ( shelf == null ) {
            return null;
        }

        ShelfDTO shelfDTO = new ShelfDTO();

        shelfDTO.setId( shelf.getId() );
        shelfDTO.setMaxCapacity( shelf.getMaxCapacity() );
        shelfDTO.setCurrentCapacity( shelf.getCurrentCapacity() );

        return shelfDTO;
    }

    @Override
    public Shelf shelfDTOToShelf(ShelfDTO shelfDTO) {
        if ( shelfDTO == null ) {
            return null;
        }

        Shelf shelf = new Shelf();

        shelf.setId( shelfDTO.getId() );
        if ( shelfDTO.getMaxCapacity() != null ) {
            shelf.setMaxCapacity( shelfDTO.getMaxCapacity() );
        }
        shelf.setCurrentCapacity( shelfDTO.getCurrentCapacity() );

        return shelf;
    }


}
