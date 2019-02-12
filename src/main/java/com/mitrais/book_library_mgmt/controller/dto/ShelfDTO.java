package com.mitrais.book_library_mgmt.controller.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ShelfDTO implements Serializable {

    private String id;

    @NotNull
    @Size(max = 1000)
    private Long maxCapacity;

    @NotNull
    @Size(max = 1000)
    private Long currentCapacity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Long maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Long getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(Long currentCapacity) {
        this.currentCapacity = currentCapacity;
    }
}
