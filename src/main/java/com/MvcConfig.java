package com;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration

public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override

    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("processor");

        registry.addViewController("/shortenURL").setViewName("processor");


    }

    @Bean

 public InternalResourceViewResolver viewResolver() {

  InternalResourceViewResolver resolver = new InternalResourceViewResolver();

  resolver.setPrefix("/WEB-INF/jsp/");

  resolver.setSuffix(".jsp");

  return resolver;

 }

}