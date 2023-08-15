package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.domain.ProductInfo;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ProductService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceUsingCompletableFutureExceptionTest {

    @Mock
    private ReviewService reviewService;
    @Mock
    private ProductInfoService productInfoService;
    @Mock
    private InventoryService inventoryService;

    @InjectMocks
    private ProductServiceUsingCompletableFuture pscf;

    @Test
    void retrieveProductDetailsWithInventory_approach2_review_service_error() {
        //given
        String productId = "P123";
        when(productInfoService.retrieveProductInfo(productId)).thenCallRealMethod();
        when(inventoryService.retrieveInventory(any())).thenCallRealMethod();
        when(reviewService.retrieveReviews(productId)).thenThrow(new RuntimeException("runtime exception"));
        //when
        Product product = pscf.retrieveProductDetailsWithInventory_approach2(productId);
        //then
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        product.getProductInfo().getProductOptions()
                        .forEach(productOption -> {
                            assertNotNull(productOption.getInventory());
                        });
        assertNotNull(product.getReview());
        assertEquals(0, product.getReview().getNoOfReviews());
    }

    @Test
    void retrieveProductDetailsWithInventory_approach2_productInfo_service_error() {
        //given
        String productId = "P123";
        when(productInfoService.retrieveProductInfo(productId)).thenThrow(new RuntimeException("runtime exception"));
        when(reviewService.retrieveReviews(productId)).thenCallRealMethod();

        //then
        assertThrows(RuntimeException.class, () -> pscf.retrieveProductDetailsWithInventory_approach2(productId));
    }

    @Test
    void retrieveProductDetailsWithInventory_approach2_inventory_service_error() {
        //given
        String productId = "P123";
        when(productInfoService.retrieveProductInfo(productId)).thenCallRealMethod();
        when(inventoryService.retrieveInventory(any())).thenThrow(new RuntimeException("runtime exception"));
        when(reviewService.retrieveReviews(productId)).thenCallRealMethod();
        //when
        Product product = pscf.retrieveProductDetailsWithInventory_approach2(productId);
        //then
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        product.getProductInfo().getProductOptions()
                .forEach(productOption -> {
                    assertNotNull(productOption.getInventory());
                    assertEquals(1, productOption.getInventory().getCount());
                });
        assertNotNull(product.getReview());
        assertTrue(product.getReview().getNoOfReviews() > 0);
    }
}