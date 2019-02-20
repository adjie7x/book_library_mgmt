package com.mitrais.book_library_mgmt.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mitrais.book_library_mgmt.controller.LibraryController;
import com.mitrais.book_library_mgmt.service.dto.ShelfDTO;
import com.mitrais.book_library_mgmt.service.impl.AuditLogServiceImpl;
import com.mitrais.book_library_mgmt.service.impl.BookServiceImpl;
import com.mitrais.book_library_mgmt.service.impl.ShelfServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(LibraryController.class)
public class LibraryControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookServiceImpl bookService;

    @MockBean
    private ShelfServiceImpl shelfService;

    @MockBean
    private AuditLogServiceImpl auditLogService;

    @Test
    public void findShelfById() throws  Exception{

        ShelfDTO shelfDTO = new ShelfDTO();
        shelfDTO.setId("sh0001");
        shelfDTO.setCurrentCapacity(Long.valueOf(9));
        shelfDTO.setMaxCapacity(Long.valueOf(10));

        when(shelfService.findShelfById(anyString())).thenReturn(
                shelfDTO
        );

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/library/find")
                .param("id", shelfDTO.getId())
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{id:sh0001,maxCapacity:10,currentCapacity:9}"))
                .andReturn();

    }

}
