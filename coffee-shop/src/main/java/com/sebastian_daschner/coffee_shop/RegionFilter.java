package com.sebastian_daschner.coffee_shop;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class RegionFilter implements ContainerResponseFilter {

    @Inject
    @Config("region")
    String region;

    private static final String REGION_NAME = "Region";

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        responseContext.getHeaders().add(REGION_NAME, region);
    }

}
