package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceUsingCompletableFutureTest {

    private ProductInfoService productInfoService = new ProductInfoService();
    private ReviewService reviewService = new ReviewService();
    private InventoryService inventoryService = new InventoryService();
    ProductServiceUsingCompletableFuture pscf = new ProductServiceUsingCompletableFuture(productInfoService, reviewService, inventoryService);
    @Test
    void retrieveProductDetails() {

        // given
        String productId = "P123";
        // when
        Product product = pscf.retrieveProductDetails(productId);
        // then
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 1);
        assertNotNull(product.getReview());
    }

    @Test
    void retrieveProductDetails_2ndApproach() {
        // given
        String productId = "P123";
        startTimer();
        // when
        CompletableFuture<Product> productCompletableFuture = pscf.retrieveProductDetails_2ndApproach(productId);
        // then
        productCompletableFuture.thenAccept((product -> {
            assertNotNull(product);
            assertTrue(product.getProductInfo().getProductOptions().size() > 1);
            assertNotNull(product.getReview());
        })).join();
        timeTaken();
    }

    @Test
    void retrieveProductDetailsWithInventory() {
        // given
        String productId = "P123";
        // when
        Product product = pscf.retrieveProductDetailsWithInventory(productId);
        // then
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        product.getProductInfo().getProductOptions()
                .forEach(productOption -> {
                    assertNotNull(productOption.getInventory());
                });
        assertNotNull(product.getReview());
    }

    @Test
    void retrieveProductDetailsWithInventory_approach2() {
        // given
        String productId = "P123";
        // when
        Product product = pscf.retrieveProductDetailsWithInventory_approach2(productId);
        // then
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        product.getProductInfo().getProductOptions()
                .forEach(productOption -> {
                    assertNotNull(productOption.getInventory());
                });
        assertNotNull(product.getReview());
    }
}