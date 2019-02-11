package com.mitrais.book_library_mgmt.controller.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ShelfDTO {

    private String id;

    @NotNull
    @Size(max = 1000)
    private Long maxCapacity;

    @NotNull
    @Size(max = 1000)
    private Long currentCapacity;

}
