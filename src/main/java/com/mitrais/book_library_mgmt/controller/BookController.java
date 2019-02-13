package com.mitrais.book_library_mgmt.controller;


import com.mitrais.book_library_mgmt.controller.utility.HeaderUtil;
import com.mitrais.book_library_mgmt.service.BookService;
import com.mitrais.book_library_mgmt.service.dto.BookDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class BookController extends BaseController{

    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    @Qualifier("bookService")
    BookService bookService;


    @PostMapping("/create")
    public ResponseEntity<BookDTO> createBook(HttpServletRequest request, @Valid @RequestBody BookDTO bookDTO) throws URISyntaxException {
        String methodName = "::createShelf";
        logger.info("========= createShelf in=========");

        ResponseEntity<BookDTO> responseEntity = null;

        BookDTO resultDto = null;
        try {
            if (bookDTO.getId() != null){
                responseEntity = ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("shelf", "idexists", "A new Shelf cannot already have an ID")).body(null);
            }else{
                resultDto = bookService.create(bookDTO);
                responseEntity = Optional.ofNullable(resultDto)
                        .map(result -> new ResponseEntity<>(
                                result,
                                HttpStatus.OK))
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
            }

        }catch (Exception e){
            e.printStackTrace();
            responseEntity = ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("book", "exception", e.getMessage())).body(null);
        }finally {
            loggingDTONoEncrypt(request, methodName, bookDTO, responseEntity);
        }

        logger.info("========= createShelf out=========");
        return responseEntity;

    }


}
