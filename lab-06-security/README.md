# Lab 06 - Security

## Mutual TLS

Another extra requirement has been raised for our API: it will be exclusively used from within a Mobile app. To avoid
other clients being able to call our APIs we're going to introduce security measures.

We want to do TLS/SSL termination in our app. Additionally, we also want to verify client certificates to establish
mutual TLS, this client certificate will be securely packaged by the Mobile app team in the Mobile distributions. Calls
without the client certificate will not be allowed to make any API calls!

We'll use Spring Boot and more specifically Tomcat's support to accomplish this.

First of all, for your convenience, a keystore and truststore have been provided to test this locally. Refer
to [this article](https://en.wikipedia.org/wiki/Java_KeyStore) for background info.

Copy the entire ``keystore`` folder to your application's ``src/main/resources`` folder.

### Enable https

First of all we're going to enable https, we use a simple self-signed certificate that is present in the
copied ``local-keystore.jks`` file.

Add these properties to your ``application.properties`` file:

```properties
# TLS settings
server.port=8443
server.ssl.enabled=true
server.ssl.key-store=classpath:keystore/local-keystore.jks
server.ssl.key-store-password=hunter2
server.ssl.key-alias=localhost
```

Restart your application and check if https is working by accessing https://localhost:8443/actuator

You should get a warning message since we use a simple self-signed certificate that is not trusted by a third party that
your browser trusts.

Depending on your browser you have the option to proceed, do so and verify the actuator endpoints are still working.

### Enable client certification validation

Now we can also enable client certification, this is done by adding a trust store (a store of certificates the server
trusts!).

Add these properties:

```properties
server.ssl.trust-store=classpath:keystore/local-truststore.jks
server.ssl.trust-store-password=hunter2
server.ssl.client-auth=need
```

Restart your application again to reach the actuator endpoint https://localhost:8443/actuator

It should fail! Depending on the browser you'll get a message indicating no trusted secure connection can be
established, example from Chrome:

> This site can’t provide a secure connection localhost didn’t accept your login certificate, or one may not have been
> provided.
> Try contacting the system admin.
> ERR_BAD_SSL_CLIENT_AUTH_CERT

This is of course exactly what we wanted, our browser does not provide a valid client certificate and our application
correctly rejects the request.

Now we still need to verify that clients which actually present a valid client certificate can make a request. This lab
contains the client ``cert`` (and its private key) that is trusted. We can use ``curl`` to present it alongside a
request:

```shell
curl -v --insecure --key lab-06-security/local-client.pem --cert lab-06-security/local-client.pem https://localhost:8443/actuator/health
``` 

This should result in an http ``200 OK`` successful response! We have now secured our app.

### Add http connector

For convenience during further development we're going to re-enable simple and plain http, this way we will not have to
provide the client certificate during local development. This is also important for our OpenShift deployment, the
readiness and liveness probes depend on the actuator endpoint!

This can be done by registering an extra http connector:

```java
//Custom tomcat configuration, we add a connector that allows http traffic next to https
@Bean
public ServletWebServerFactory servletContainer(@Value("${server.http.port}") int httpPort){
        Connector connector=new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setPort(httpPort);

        TomcatServletWebServerFactory tomcat=new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;
        }
```

Note the auto-injected property that provides the ``http`` port, add it in ``application.properties``:

```properties
# http connector
server.http.port=8080
```

Restart your application, the http actuator endpoint should be reachable again http://localhost:8080/actuator

## Commit and tag your work

Commit your work: use the lab name as comment and tag it with the same name. Don't forget to push to Github.