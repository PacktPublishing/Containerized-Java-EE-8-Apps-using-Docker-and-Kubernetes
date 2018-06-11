package com.sebastian_daschner.coffee_shop;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.ServiceUnavailableException;
import java.util.concurrent.atomic.AtomicBoolean;

@Path("health")
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class HealthResource {

    private final AtomicBoolean shutdown = new AtomicBoolean();

    @GET
    public String health() {
        if (shutdown.get())
            throw new ServiceUnavailableException();

        return "OK";
    }

    @DELETE
    public void shutdown() {
        shutdown.set(true);
    }

}
