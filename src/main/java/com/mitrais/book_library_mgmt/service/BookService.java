package com.mitrais.book_library_mgmt.service;

import com.mitrais.book_library_mgmt.service.dto.BookDTO;

public interface BookService {

    public BookDTO create(BookDTO bookDTO) throws Exception;
}
