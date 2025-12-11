package com.univ.soa.billing_service.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        // Expose SOAP at /ws/*
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "invoices") // This defines the WSDL URL: /ws/invoices.wsdl
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema invoiceSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("InvoicesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://univ.com/soa/billing/soap");
        wsdl11Definition.setSchema(invoiceSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema invoiceSchema() {
        return new SimpleXsdSchema(new ClassPathResource("invoices.xsd"));
    }
}