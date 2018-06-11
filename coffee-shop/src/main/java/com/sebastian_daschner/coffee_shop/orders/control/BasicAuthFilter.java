package com.sebastian_daschner.coffee_shop.orders.control;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

class BasicAuthFilter implements ClientRequestFilter {

    private final String user;
    private final String password;

    BasicAuthFilter(String user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        MultivaluedMap<String, Object> headers = requestContext.getHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, getBasicAuthentication());

    }

    private String getBasicAuthentication() throws UnsupportedEncodingException {
        String token = user + ":" + password;
        return "BASIC " + Base64.getEncoder().encodeToString(token.getBytes("UTF-8"));
    }

}
