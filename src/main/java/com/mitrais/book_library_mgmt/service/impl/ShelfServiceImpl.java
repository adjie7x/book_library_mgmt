package com.mitrais.book_library_mgmt.service.impl;

import com.mitrais.book_library_mgmt.service.dto.ShelfDTO;
import com.mitrais.book_library_mgmt.service.mapper.ShelfDTOMapper;
import com.mitrais.book_library_mgmt.model.Shelf;
import com.mitrais.book_library_mgmt.repository.ShelfRepository;
import com.mitrais.book_library_mgmt.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public ShelfDTO create(ShelfDTO shelfDTO) throws Exception {

        Shelf shelf = ShelfDTOMapper.INSTANCE.shelfDTOToShelf(shelfDTO);
        shelf = shelfRepository.save(shelf);

        return ShelfDTOMapper.INSTANCE.shelfToShelfDTO(shelf);
    }

    @Override
    public ShelfDTO findShelfById(String shelfId) {

        Optional<Shelf> shelf = shelfRepository.findById(shelfId);

        return ShelfDTOMapper.INSTANCE.shelfToShelfDTO(shelf.get());
    }
}
