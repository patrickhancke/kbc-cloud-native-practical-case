package com.ezgroceries.shopinglist;

import config.ProjectConfig;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@EnableFeignClients
@SpringBootApplication
@Import({ProjectConfig.class})
public class EzGroceriesShopingListApplication {

    public static void main(String[] args) {
        SpringApplication.run(EzGroceriesShopingListApplication.class, args);
    }

    @Bean
    public ServletWebServerFactory servletContainer(@Value("${server.http.port}") int httpPort){
        Connector connector=new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setPort(httpPort);

        TomcatServletWebServerFactory tomcat=new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;
    }

}
