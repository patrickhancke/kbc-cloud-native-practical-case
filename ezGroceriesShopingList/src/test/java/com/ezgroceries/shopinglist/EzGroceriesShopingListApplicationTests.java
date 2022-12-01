package com.ezgroceries.shopinglist;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("hsqldb")
class EzGroceriesShopingListApplicationTests {
    @Autowired
    ApplicationContext context;
    @Test
    void contextLoads() {
        assertNotNull(context);
    }

    @Test
    void testServletWebServerFactory() {
        TomcatServletWebServerFactory servletContainer = (TomcatServletWebServerFactory) context.getBean("servletContainer");

        assertThat(servletContainer.getAdditionalTomcatConnectors().get(0).getPort(), equalTo(8080));
        assertThat(servletContainer.getPort(), equalTo(8443));
    }

}
