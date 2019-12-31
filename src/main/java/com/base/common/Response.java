package com.base.common;

/**
 * Response
 *
 * @author zhangqiao
 * @since 2018-12-31 14:00
 */

import java.io.Serializable;

public class Response implements Serializable {
    private String responseStatus;
    private String[] responseMessage;
    private Object responseBody;

    public Response() {
    }

    public static final Response successful(Object responseBody) {
        Response response = new Response();
        response.responseStatus = "success";
        response.responseBody = responseBody;
        return response;
    }

    public static final Response failure(String[] message) {
        Response response = new Response();
        response.responseStatus = "failure";
        response.responseMessage = message;
        return response;
    }

    public static final Response failure(Object responseBody, String[] message) {
        Response response = new Response();
        response.responseStatus = "failure";
        response.responseMessage = message;
        response.responseBody = responseBody;
        return response;
    }

    public String getResponseStatus() {
        return this.responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String[] getResponseMessage() {
        return this.responseMessage;
    }

    public void setResponseMessage(String[] responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Object getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }
}

