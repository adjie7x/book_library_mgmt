package com.mitrais.book_library_mgmt.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitrais.book_library_mgmt.controller.LibraryController;
import com.mitrais.book_library_mgmt.service.dto.BookDTO;
import com.mitrais.book_library_mgmt.service.dto.BookShelfDTO;
import com.mitrais.book_library_mgmt.service.dto.ShelfDTO;
import com.mitrais.book_library_mgmt.service.impl.AuditLogServiceImpl;
import com.mitrais.book_library_mgmt.service.impl.BookServiceImpl;
import com.mitrais.book_library_mgmt.service.impl.ShelfServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebMvcTest(LibraryController.class)
public class LibraryControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webAppContext;

    @MockBean
    private BookServiceImpl bookService;

    @MockBean
    private ShelfServiceImpl shelfService;

    @MockBean
    private AuditLogServiceImpl auditLogService;

    @Before
    public void setup() {
//        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }


    @Test
    @Ignore
    public void helloTest() throws  Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/hello-world")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{id:sh0001,maxCapacity:10,currentCapacity:9}"))
                .andReturn();

    }

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
                .andDo(print())
                .andReturn();

    }

    @Test
    public void findShelfByIdButNoData() throws  Exception{

        ShelfDTO shelfDTO = new ShelfDTO();
        shelfDTO.setId("sh0001");
        shelfDTO.setCurrentCapacity(Long.valueOf(9));
        shelfDTO.setMaxCapacity(Long.valueOf(10));

        when(shelfService.findShelfById(anyString())).thenReturn(
                null
        );

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/library/find")
                .param("id", shelfDTO.getId())
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andDo(print())
                .andReturn();

    }

    @Test
    public void successAddBookToShelf() throws Exception{
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId("b0001");
        bookDTO.setAuthor("author");
        bookDTO.setIsbn("isbn");
        bookDTO.setShelfId("s0001");
        bookDTO.setTitle("tiltle");

        when(bookService.addBookIntoShelf(any(BookShelfDTO.class))).thenReturn(
                bookDTO
        );

        BookShelfDTO bookShelfDTO = new BookShelfDTO();
        bookShelfDTO.setBookId("b0001");
        bookShelfDTO.setShelfId("s0001");

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(bookShelfDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/library/add")
                .content(jsonStr)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{id:b0001,isbn:isbn,title:tiltle,author:author,shelfId:s0001}"))
                .andDo(print())
                .andReturn();

    }

}
