package com.mitrais.book_library_mgmt.service.dto;

import javax.validation.constraints.NotNull;

public class BookShelfDTO {

    @NotNull
    private String bookId;

    @NotNull
    private String shelfId;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getShelfId() {
        return shelfId;
    }

    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }
}
