package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;
import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {

    PriceValidatorService priceValidatorService = new PriceValidatorService();
    CheckoutService checkoutService = new CheckoutService(priceValidatorService);

    @Test
    void checkout_6_items(){
        //given
        Cart cart = DataSet.createCart(6);
        //when
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);
        //then
        assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());
        assertTrue(checkoutResponse.getFinalRate() > 0);
    }

    @Test
    void checkout_9_items(){
        //given
        Cart cart = DataSet.createCart(9);
        //when
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);
        //then
        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
    }

    @Test
    void no_of_cors() {
        System.out.println("no. of cors :: " + Runtime.getRuntime().availableProcessors());
    }
}