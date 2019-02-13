package com.mitrais.book_library_mgmt.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mitrais.book_library_mgmt.service.dto.ShelfDTO;
import com.mitrais.book_library_mgmt.controller.utility.HeaderUtil;
import com.mitrais.book_library_mgmt.model.RestResponse;
import com.mitrais.book_library_mgmt.model.Shelf;
import com.mitrais.book_library_mgmt.service.ShelfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shelf")
public class ShelfController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(ShelfController.class);

    @Autowired
    @Qualifier("shelfService")
    ShelfService shelfService;

//    @Inject
//    private ShelfDTOMapper shelfDTOMapper;

    @PostMapping("/add")
    public RestResponse addShelf(HttpServletRequest request, @RequestBody String requestBody){

        String methodName = "::addShelf";
        logger.info("========= addShelf in=========");

        RestResponse response = getDefaultResponse();

        try {

            Shelf shelfRequest = (Shelf) getNoEncryptObjectsMapper(requestBody, Shelf.class);
            response = getSuccessResponse(shelfService.add(shelfRequest));

        }catch (JsonParseException e) {
            e.printStackTrace();
            response = getFailedResponse(e.toString());
        } catch (JsonMappingException e) {
            e.printStackTrace();
            response = getFailedResponse(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            response = getFailedResponse(e.toString());
        }catch (Exception e){
            e.printStackTrace();
            response = getFailedResponse(e.toString());
        } finally {
            loggingNoEncrypt(request, methodName, requestBody, response);
        }


        logger.info("========= addShelf out=========");

        return response;

    }

    @PostMapping("/search")
    public RestResponse searchShelf(HttpServletRequest request, @RequestBody String requestBody){
        String methodName = "::searchShelf";
        logger.info("========= searchShelf in=========");

        RestResponse response = getDefaultResponse();

        try {

            Shelf shelfRequest = (Shelf) getNoEncryptObjectsMapper(requestBody, Shelf.class);

            List<Shelf> shelfList = shelfService.search(shelfRequest);
            response = getSuccessResponse(shelfList);


        }catch (JsonParseException e) {
            e.printStackTrace();
            response = getFailedResponse(e);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            response = getFailedResponse(e);
        } catch (IOException e) {
            e.printStackTrace();
            response = getFailedResponse(e);
        }catch (Exception e){
            e.printStackTrace();
            response = getFailedResponse(e);
        } finally {
            loggingNoEncrypt(request, methodName, requestBody, response);
        }


        logger.info("========= searchShelf out=========");

        return response;
    }

    @PostMapping("/create")
    public ResponseEntity<ShelfDTO> createShelf(HttpServletRequest request, @Valid @RequestBody ShelfDTO shelfDTO) throws URISyntaxException{
        String methodName = "::createShelf";
        logger.info("========= createShelf in=========");

        ResponseEntity<ShelfDTO> responseEntity = null;

        ShelfDTO resultDto = null;
        try {
            if (shelfDTO.getId() != null){
                responseEntity = ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("shelf", "idexists", "A new Shelf cannot already have an ID")).body(null);
            }else{
                resultDto = shelfService.create(shelfDTO);
                responseEntity = Optional.ofNullable(resultDto)
                        .map(result -> new ResponseEntity<>(
                                result,
                                HttpStatus.OK))
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
            }

        }catch (Exception e){
            e.printStackTrace();
            responseEntity = ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("shelf", "exception", e.getMessage())).body(null);
        }finally {
            loggingDTONoEncrypt(request, methodName, shelfDTO, responseEntity);
        }

        logger.info("========= createShelf out=========");
         return responseEntity;

    }

}
