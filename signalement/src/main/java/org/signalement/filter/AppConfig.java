/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.signalement.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Mamitiana
 */
@Configuration
public class AppConfig {

 @Bean
 public FilterRegistrationBean < CustomURLFilter > filterRegistrationBean() {
  FilterRegistrationBean < CustomURLFilter > registrationBean = new FilterRegistrationBean();
  CustomURLFilter customURLFilter = new CustomURLFilter();

  registrationBean.setFilter(customURLFilter);
  registrationBean.addUrlPatterns("/admin/*");
  registrationBean.setOrder(2); //set precedence
  return registrationBean;
 }
 
 @Bean(name="filterRegistrationBeanService")
 public FilterRegistrationBean < ServiceFilter > filterRegistrationBeanService() {
  FilterRegistrationBean < ServiceFilter > registrationBean = new FilterRegistrationBean();
    ServiceFilter serviceFilter = new ServiceFilter();

  registrationBean.setFilter(serviceFilter);
  registrationBean.addUrlPatterns("/wb/*");
  registrationBean.setOrder(3); //set precedence
  return registrationBean;
 }
 
}
