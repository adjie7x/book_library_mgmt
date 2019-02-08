package com.mitrais.book_library_mgmt.model;

public class RestResponse {
	
	String statusCode;
	String statusMessage;
	Object response;
	
	public RestResponse(String statusCode, Object response){
		this.statusCode = statusCode;
		this.response = response;
	}

	public RestResponse(String statusCode, String statusMessage, Object response){
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.response = response;
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the response
	 */
	public Object getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(Object response) {
		this.response = response;
	}
	
	
	

}
