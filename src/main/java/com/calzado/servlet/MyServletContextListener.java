package com.calzado.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.calzado.util.ApplicationProperties;

import org.apache.commons.configuration2.ex.ConfigurationException;

@WebListener
public class MyServletContextListener implements ServletContextListener {

     public void contextInitialized(final ServletContextEvent sce) {
          System.out.println("#####contextInitialized ");

          try {
               ApplicationProperties.getInstance();
          } catch (ConfigurationException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
          }
     }
       
     public void contextDestroyed(final ServletContextEvent sce) {
           System.out.print("######contextDestroyed");
 }
 }