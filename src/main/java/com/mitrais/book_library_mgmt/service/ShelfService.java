package com.mitrais.book_library_mgmt.service;

import com.mitrais.book_library_mgmt.service.dto.ShelfDTO;
import com.mitrais.book_library_mgmt.model.Shelf;

import java.util.List;

public interface ShelfService {

    public List<Shelf> search(Shelf shelf);

    public Shelf add(Shelf shelf);

    public ShelfDTO create(ShelfDTO shelfDTO) throws Exception;

}
