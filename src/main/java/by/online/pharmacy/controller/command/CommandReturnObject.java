package by.online.pharmacy.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandReturnObject {
    private  String page;
    private  HttpServletResponse response;
    private  HttpServletRequest request;

    public CommandReturnObject() {

    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getPage() {
        return page;

    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public HttpServletRequest getRequest() {
        return request;
    }
}
