package com.mitrais.book_library_mgmt.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitrais.book_library_mgmt.model.AuditLog;
import com.mitrais.book_library_mgmt.model.RestResponse;
import com.mitrais.book_library_mgmt.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@Controller
public class BaseController {

    @Autowired
    @Qualifier("auditLogService")
    AuditLogService auditLogService;


    protected <T> Object getObjectsMapper(String content, Class<T> valueType) throws JsonParseException, JsonMappingException, IOException{

        String plainRequest = getPlainRequestBody(content);

        ObjectMapper mapper = new ObjectMapper();
        return (T)mapper.readValue(plainRequest, valueType);
    }

    protected <T> Object getNoEncryptObjectsMapper(String content, Class<T> valueType) throws JsonParseException, JsonMappingException, IOException{

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
        return (T)mapper.readValue(content, valueType);
    }

    private String getJsonStringFromObject(Object object){
        String jsonString = "";
        ObjectMapper mapper = new ObjectMapper();

        try {
            jsonString = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonString;

    }

    protected RestResponse initializeDefaultResponse(String status, Object description){
        RestResponse resp = new RestResponse(status, description);
        return resp;
    }

    protected RestResponse getDefaultResponse(){
        return initializeDefaultResponse("404", "Invalid request format");
    }

    protected RestResponse getSuccessDefaultResponse(){
        return initializeDefaultResponse("200", "Success");
    }

    protected RestResponse getFailedDefaultResponse(){
        return initializeDefaultResponse("200", "Failed");
    }

    protected RestResponse getSuccessResponse(Object object){
        return initializeDefaultResponse("200", object);
    }

    protected RestResponse getFailedResponse(Object object){
        return initializeDefaultResponse("500", object);
    }

    protected void logging(HttpServletRequest request, String methodName, String requestPayload, RestResponse responsePayload){

        AuditLog log = new AuditLog();
        log.setCreatedDate(new Date());
        log.setChannel("LIBMGMT_WEB");
        log.setOperationName(methodName);
        log.setResponsePayload(getJsonStringFromObject(responsePayload.getResponse()));
        log.setEndPoint("");

        //Karena di database column service name 50, ada kemungkinan method GET servlet path >50. maka di split
        log.setServiceName(request.getServletPath().length() > 50 ? request.getServletPath().substring(0, 50) : request.getServletPath());
        try {
            log.setRequestPayload(getPlainRequestBody(requestPayload));
        } catch (IOException e) {
            log.setRequestPayload(requestPayload);
            e.printStackTrace();
        }finally{
            auditLogService.insert(log);
        }



    }

    protected void loggingNoEncrypt(HttpServletRequest request, String methodName, String requestPayload, RestResponse responsePayload){

        AuditLog log = new AuditLog();
        log.setCreatedDate(new Date());
        log.setChannel("LIBMGMT_WEB");
        log.setOperationName(methodName);
        log.setResponsePayload(getJsonStringFromObject(responsePayload));
        log.setEndPoint("");

        //Karena di database column service name 50, ada kemungkinan method GET servlet path >50. maka di split
        log.setServiceName(request.getServletPath().length() > 50 ? request.getServletPath().substring(0, 50) : request.getServletPath());
        log.setRequestPayload(requestPayload);
        auditLogService.insert(log);

    }

    protected void loggingDTONoEncrypt(HttpServletRequest request, String methodName, Object object, ResponseEntity responseEntity){

        AuditLog log = new AuditLog();
        log.setCreatedDate(new Date());
        log.setChannel("LIBMGMT_WEB");
        log.setOperationName(methodName);
        log.setResponsePayload(getJsonStringFromObject(responseEntity));
        log.setEndPoint("");

        //Karena di database column service name 50, ada kemungkinan method GET servlet path >50. maka di split
        log.setServiceName(request.getServletPath().length() > 50 ? request.getServletPath().substring(0, 50) : request.getServletPath());
        log.setRequestPayload(getJsonStringFromObject(object));
        auditLogService.insert(log);

    }

    protected String getPlainRequestBody(String data) throws IOException{

//        return secChecker.getPlainRequestBody(data);
        return "";
    }

}
