package de.mirkosertic.portalprototype;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InvoiceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        if (aRequest.getRequestURI().endsWith("list")) {
            aRequest.getRequestDispatcher("/invoicelist.ftl").include(aRequest, aResponse);
        } else {
            aRequest.getRequestDispatcher("/singleinvoice.ftl").include(aRequest, aResponse);
        }
    }
}
