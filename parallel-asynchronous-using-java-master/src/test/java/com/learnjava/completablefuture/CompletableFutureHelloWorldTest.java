package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureHelloWorldTest {
    HelloWorldService hws = new HelloWorldService();
    CompletableFutureHelloWorld cfhw = new CompletableFutureHelloWorld(hws);

    @Test
    void helloWorld() {
        //Given

        //when
        CompletableFuture<String> completableFuture = cfhw.helloWorld();
        //then
        completableFuture.thenAccept(s->{
            assertEquals("HELLO WORLD", s);
        }).join();
    }


    @Test
    void helloWorld_withSize() {
        CompletableFuture<String> completableFuture = cfhw.helloWorld_withSize();
        completableFuture.thenAccept(s->{
            assertEquals("11 - HELLO WORLD", s);
        }).join();
    }

    @Test
    void helloWorldMultipleAsyncCalls() {
        String result = cfhw.helloWorldMultipleAsyncCalls();
        //then
        assertEquals("HELLO WORLD!", result);
    }

    @Test
    void helloWorldThreeAsyncCalls() {
        String result = cfhw.helloWorldThreeAsyncCalls();
        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorldThreeAsyncCalls_log() {
        String result = cfhw.helloWorldThreeAsyncCalls_log();
        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorldThreeAsyncCalls_log_async() {
        String result = cfhw.helloWorldThreeAsyncCalls_log_async();
        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }
    @Test
    void helloWorldThreeAsyncCalls_custom_thread_pool() {
        String result = cfhw.helloWorldThreeAsyncCalls_custom_thread_pool();
        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }
    @Test
    void helloWorldThreeAsyncCalls_custom_thread_pool_async() {
        String result = cfhw.helloWorldThreeAsyncCalls_custom_thread_pool_async();
        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorld_4_async_calls() {
        String result = cfhw.helloWorld_4_async_calls();
        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE! BYE!", result);
    }

    @Test
    void helloWorld_thenCompose() {
        //Given
        startTimer();
        //when
        CompletableFuture<String> completableFuture = cfhw.helloWorldThenCompose();
        //then
        completableFuture.thenAccept(s->{
            assertEquals("HELLO WORLD!", s);
        }).join();

        timeTaken();
    }
}