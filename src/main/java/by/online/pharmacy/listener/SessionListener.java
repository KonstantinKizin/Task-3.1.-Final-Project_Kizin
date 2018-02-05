package by.online.pharmacy.listener;

import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute(WebProperty.USER_ATTRIBUTE_ROLE, WebProperty.GUEST_ROLE);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
