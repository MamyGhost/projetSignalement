/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.signalement.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.signalement.entities.Tokenfront;
import org.signalement.entities.Tokenmobile;
import org.signalement.entities.Userfront;
import org.signalement.repository.SignalementRepository;
import org.signalement.repository.TokenfrontRepository;
import org.signalement.repository.TokenmobileRepository;
import org.signalement.repository.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 *
 * @author Mamitiana
 */
public class ServiceFilter implements Filter {
      
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceFilter.class);
        
    
    private UtilisateurRepository utilisateurRepository;
    
    
    private TokenmobileRepository tokenmobileRepository;
     
    
    private TokenfrontRepository tokenfrontRepository;
    
 
   
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("########## Initiating ServiceFilter filter ##########");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ServletContext servletContext = request.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        tokenfrontRepository = webApplicationContext.getBean(TokenfrontRepository.class);
        tokenmobileRepository = webApplicationContext.getBean(TokenmobileRepository.class);
        utilisateurRepository = webApplicationContext.getBean(UtilisateurRepository.class);

        LOGGER.info("This Filter is only called when request is mapped for /customer resource");

        //call next filter in the filter chain
         String path = request.getRequestURI();
        if ("/wb/userFront/login".equals(path) || "/wb/utilisateur/login".equals(path)) {
            filterChain.doFilter(request, response);
            return;
        }
        String auth = request.getHeader("Authorization");
         if(auth == null){
              response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalide token");
             return;
         } 
         System.out.println("TOKENNNNNNNNN: "+auth);
        String authtoken = auth.replace("Bearer ","");
         System.out.println("TOKENNNNNNNNN 2: "+authtoken);
           SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
         Tokenfront t = tokenfrontRepository.findByToken(authtoken);
             System.out.println("TOKENFRONT : "+t);
             if( t != null){
                        if(t.getDateexp().after(new Date()))
                   {
                       filterChain.doFilter(request, response);
                       LOGGER.info("Logging Response :{}", response.getContentType());
                       return;
                   }
             }
             Tokenmobile tm = tokenmobileRepository.findByToken(authtoken);
             System.out.println("TOKENMOBILE : "+tm);
           if( tm != null){
            if(tm.getDateexp().after(new Date()))
            {
                filterChain.doFilter(request, response);
                LOGGER.info("Logging Response :{}", response.getContentType());
                return;
            }
 }else{
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalide token");
           }
           
        
  
    }

    @Override
    public void destroy() {

    }
}
