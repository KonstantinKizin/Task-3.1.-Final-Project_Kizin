package by.online.pharmacy.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandReturnObject {
    private  String page;
    private  HttpServletRequest request;

    public CommandReturnObject() {

    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getPage() {
        return page;
    }


    public HttpServletRequest getRequest() {
        return request;
    }
}
