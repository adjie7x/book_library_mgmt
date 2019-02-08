package com.mitrais.book_library_mgmt.repository;

import com.mitrais.book_library_mgmt.model.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelfRepository extends JpaRepository<Shelf,String> {
}
