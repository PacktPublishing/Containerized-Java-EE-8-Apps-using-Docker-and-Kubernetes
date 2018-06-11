package com.sebastian_daschner.coffee_shop.boundary;

import com.sebastian_daschner.coffee_shop.orders.boundary.CoffeeShop;
import com.sebastian_daschner.coffee_shop.orders.entity.CoffeeType;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CoffeeShopTest {

    private CoffeeShop cut;

    @Before
    public void setUp() {
        cut = new CoffeeShop();
    }

    @Test
    public void testGetCoffeeTypes() {
        final Set<CoffeeType> coffeeTypes = cut.getCoffeeTypes();
        assertThat(coffeeTypes).hasSize(3);
        assertThat(coffeeTypes).containsOnly(CoffeeType.ESPRESSO, CoffeeType.LATTE, CoffeeType.POUR_OVER);
    }

}