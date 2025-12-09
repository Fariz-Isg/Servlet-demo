package com.example.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        urlPatterns = {"/second"},
        initParams = {
                @WebInitParam(name = "appName", value = "My Second Servlet"),
                @WebInitParam(name = "resourcePath", value = "/static/css")
        }
)
public class SecondServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String appName = getInitParameter("appName");
        String resourcePath = getInitParameter("resourcePath");

        ServletContext context = getServletContext();
        Integer counter = (Integer) context.getAttribute("visitCounter");

        if (counter == null) {
            counter = 0;
        }
        counter++;
        context.setAttribute("visitCounter", counter);

        String sharedMsg = (String) context.getAttribute("sharedMessage");
        if (sharedMsg == null) {
            sharedMsg = "No message yet";
        }
        context.setAttribute("sharedMessage", "Hello from SecondServlet!");

        out.println("<html><body>");
        out.println("<h2>Second Servlet</h2>");
        out.println("<p><strong>App Name:</strong> " + appName + "</p>");
        out.println("<p><strong>Resource Path:</strong> " + resourcePath + "</p>");
        out.println("<p><strong>Visit Counter:</strong> " + counter + "</p>");
        out.println("<p><strong>Shared Message:</strong> " + sharedMsg + "</p>");
        out.println("<p><a href='first'>Go to First Servlet</a></p>");
        out.println("</body></html>");
    }
}