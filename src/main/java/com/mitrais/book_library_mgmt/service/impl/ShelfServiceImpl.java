package com.mitrais.book_library_mgmt.service.impl;

import com.mitrais.book_library_mgmt.model.Shelf;
import com.mitrais.book_library_mgmt.repository.ShelfRepository;
import com.mitrais.book_library_mgmt.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("shelfService")
public class ShelfServiceImpl implements ShelfService {

    @Autowired
    ShelfRepository shelfRepository;

    @Override
    public List<Shelf> search(Shelf shelf) {

        List<Shelf> shelfList = new ArrayList<>();
        shelfList = shelfRepository.findAll();

        return shelfList;
    }

    @Override
    public Shelf add(Shelf shelf) {

        Shelf response = shelfRepository.save(shelf);

        return response;
    }
}
