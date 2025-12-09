package com.example.listeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("=================================");
        System.out.println("Application Started!");
        System.out.println("=================================");

        ServletContext context = sce.getServletContext();

        // Initialize context attributes
        context.setAttribute("visitCounter", 0);
        context.setAttribute("sharedMessage", "Application initialized");
        context.setAttribute("startTime", System.currentTimeMillis());

        System.out.println("Context attributes initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("=================================");
        System.out.println("Application Stopped!");

        ServletContext context = sce.getServletContext();
        Long startTime = (Long) context.getAttribute("startTime");

        if (startTime != null) {
            long uptime = System.currentTimeMillis() - startTime;
            System.out.println("Application uptime: " + (uptime / 1000) + " seconds");
        }

        System.out.println("=================================");
    }
}