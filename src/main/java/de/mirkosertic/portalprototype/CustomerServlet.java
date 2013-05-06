package de.mirkosertic.portalprototype;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        if (aRequest.getRequestURI().endsWith("list")) {
            aRequest.getRequestDispatcher("/customerlist.ftl").include(aRequest, aResponse);
        } else {
            aRequest.getRequestDispatcher("/singlecustomer.ftl").include(aRequest, aResponse);
        }
    }
}
