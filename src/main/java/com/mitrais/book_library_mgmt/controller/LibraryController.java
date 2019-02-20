package com.mitrais.book_library_mgmt.controller;

import com.mitrais.book_library_mgmt.controller.utility.HeaderUtil;
import com.mitrais.book_library_mgmt.service.BookService;
import com.mitrais.book_library_mgmt.service.ShelfService;
import com.mitrais.book_library_mgmt.service.dto.BookDTO;
import com.mitrais.book_library_mgmt.service.dto.BookShelfDTO;
import com.mitrais.book_library_mgmt.service.dto.ShelfDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/library")
public class LibraryController extends BaseController{

    private final Logger logger = LoggerFactory.getLogger(ShelfController.class);

    @Autowired
    @Qualifier("shelfService")
    ShelfService shelfService;

    @Autowired
    @Qualifier("bookService")
    BookService bookService;

    @GetMapping("/find")
    public ResponseEntity<ShelfDTO> findShelfById(HttpServletRequest request, @RequestParam(value = "id",required = true) String id){
        String methodName = "::findShelfById";
        logger.info("========= findShelfById in=========");

        ShelfDTO shelfDTO = shelfService.findShelfById(id);

        ResponseEntity<ShelfDTO> responseEntity = new ResponseEntity<>(shelfDTO, HttpStatus.OK);
//        loggingDTONoEncrypt(request, methodName, "{}", responseEntity);

        logger.info("========= findShelfById out=========");

        return responseEntity;
    }

    @PostMapping("/add")
    public ResponseEntity<BookDTO> addBookIntoShelf(HttpServletRequest request, @Valid @RequestBody BookShelfDTO bookShelfDTO) throws URISyntaxException {
        String methodName = "::addBookIntoShelf";
        logger.info("========= addBookIntoShelf in=========");

        ResponseEntity<BookDTO> responseEntity = null;

        BookDTO resultDto = null;
        try {
            resultDto = bookService.addBookIntoShelf(bookShelfDTO);
            responseEntity = Optional.ofNullable(resultDto)
                    .map(result -> new ResponseEntity<>(
                            result,
                            HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        }catch (Exception e){
            e.printStackTrace();
            responseEntity = ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("shelf", "exception", e.getMessage())).body(null);
        }finally {
            loggingDTONoEncrypt(request, methodName, bookShelfDTO, responseEntity);
        }

        logger.info("========= addBookIntoShelf out=========");
        return responseEntity;

    }

    @PostMapping("/remove")
    public ResponseEntity<BookDTO> removeBookFromShelf(HttpServletRequest request, @Valid @RequestBody BookShelfDTO bookShelfDTO) throws URISyntaxException {
        String methodName = "::removeBookFromShelf";
        logger.info("========= removeBookFromShelf in=========");

        ResponseEntity<BookDTO> responseEntity = null;

        BookDTO resultDto = null;
        try {
            resultDto = bookService.removeBookFromShelf(bookShelfDTO);
            responseEntity = Optional.ofNullable(resultDto)
                    .map(result -> new ResponseEntity<>(
                            result,
                            HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        }catch (Exception e){
            e.printStackTrace();
            responseEntity = ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("shelf", "exception", e.getMessage())).body(null);
        }finally {
            loggingDTONoEncrypt(request, methodName, bookShelfDTO, responseEntity);
        }

        logger.info("========= removeBookFromShelf out=========");
        return responseEntity;

    }

}
