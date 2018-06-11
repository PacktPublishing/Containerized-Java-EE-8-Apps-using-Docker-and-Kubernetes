package com.sebastian_daschner.coffee_shop.orders.control;

import com.sebastian_daschner.coffee_shop.Config;
import com.sebastian_daschner.coffee_shop.orders.entity.Order;
import com.sebastian_daschner.coffee_shop.orders.entity.OrderStatus;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class OrderProcessor {

    private static final String PROCESSOR_URI = "http://coffee-shop-processor:8080/coffee-shop-processor/resources/processes";

    private WebTarget target;

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    @Config("order.processor.user")
    String user;

    @Inject
    @Config("order.processor.password")
    String password;

    @PostConstruct
    private void initClient() {
        final Client client = ClientBuilder.newClient().register(new BasicAuthFilter(user, password));
        target = client.target(PROCESSOR_URI);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void processOrder(Order order) {
        OrderStatus status = retrieveOrderStatus(order);
        order.setStatus(status);
        entityManager.merge(order);
    }

    private OrderStatus retrieveOrderStatus(Order order) {
        JsonObject requestJson = buildRequestJson(order);

        JsonObject responseJson = sendRequest(requestJson);

        return readStatus(responseJson);
    }

    private JsonObject buildRequestJson(Order order) {
        return Json.createObjectBuilder()
                .add("order", order.getId())
                .add("type", order.getType().name().toUpperCase())
                .add("origin", order.getOrigin().getName().toUpperCase())
                .add("status", order.getStatus().name().toUpperCase())
                .build();
    }

    private JsonObject sendRequest(final JsonObject requestBody) {
        Response response = target.request().buildPost(Entity.json(requestBody)).invoke();

        if (response.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL) {
            throw new RuntimeException("Could not successfully process order, response from " + PROCESSOR_URI + " was " + response.getStatus());
        }

        return response.readEntity(JsonObject.class);
    }

    private OrderStatus readStatus(final JsonObject responseJson) {
        final OrderStatus status = OrderStatus.fromString(responseJson.getString("status", null));

        if (status == null)
            throw new RuntimeException("Could not read known status from response" + responseJson);

        return status;
    }

}
