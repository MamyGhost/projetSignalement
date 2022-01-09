/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.signalement.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.signalement.entities.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Mamitiana
 */
public class CustomURLFilter implements Filter {
      private static final Logger LOGGER = LoggerFactory.getLogger(CustomURLFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("########## Initiating CustomURLFilter filter ##########");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        LOGGER.info("This Filter is only called when request is mapped for /customer resource");
       // Admin admin = (Admin)session.getAttribute("admin");

        //call next filter in the filter chain
        filterChain.doFilter(request, response);
        LOGGER.info("Logging Response :{}", response.getContentType());
        

        
//        if( admin!= null ){
//           filterChain.doFilter(request, response);
//           LOGGER.info("Logging Response :{}", response.getContentType());
//           }
//       else{
//            RequestDispatcher rd =  request.getRequestDispatcher("/admin/login");
//            rd.forward(request, response);
//       }

    }

    @Override
    public void destroy() {

    }
}
