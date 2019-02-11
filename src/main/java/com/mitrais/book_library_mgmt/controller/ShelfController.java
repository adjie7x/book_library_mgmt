package com.mitrais.book_library_mgmt.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mitrais.book_library_mgmt.model.RestResponse;
import com.mitrais.book_library_mgmt.model.Shelf;
import com.mitrais.book_library_mgmt.service.ShelfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/shelf")
public class ShelfController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(ShelfController.class);

    @Autowired
    @Qualifier("shelfService")
    ShelfService shelfService;

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



}
