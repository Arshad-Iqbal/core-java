package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ComplatableFutureHelloWorldExceptionTest {

    @Mock
    HelloWorldService helloWorldService =  mock(HelloWorldService.class);

    @InjectMocks
    ComplatableFutureHelloWorldException cfhwe;

    @Test
    void helloWorldThreeAsyncCalls_handle() {
        //given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("runtime exception"));
        when(helloWorldService.world()).thenCallRealMethod();
        //when
        String result = cfhwe.helloWorldThreeAsyncCalls_handle();
        //then
        assertEquals(" WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorldThreeAsyncCalls_handle2() {
        //given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("runtime exception"));
        when(helloWorldService.world()).thenThrow(new RuntimeException("runtime exception"));
        //when
        String result = cfhwe.helloWorldThreeAsyncCalls_handle();
        //then
        assertEquals("** HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorldThreeAsyncCalls_handle_success() {
        //given
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();
        //when
        String result = cfhwe.helloWorldThreeAsyncCalls_handle(); // "hello world! HI COMPLETABLEFUTURE!"
        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorldThreeAsyncCalls_exceptionally_success() {
        //given
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();
        //when
        String result = cfhwe.helloWorldThreeAsyncCalls_exceptionally(); // "hello world! HI COMPLETABLEFUTURE!"
        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }
    @Test
    void helloWorldThreeAsyncCalls_exceptionally_when_2_exception() {
        //given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("runtime exception"));
        when(helloWorldService.world()).thenThrow(new RuntimeException("runtime exception"));
        //when
        String result = cfhwe.helloWorldThreeAsyncCalls_exceptionally();
        //then
        assertEquals("** HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorldThreeAsyncCalls_whenComplete_success() {
        //given
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();
        //when
        String result = cfhwe.helloWorldThreeAsyncCalls_whenComplete(); // "hello world! HI COMPLETABLEFUTURE!"
        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }
    @Test
    void helloWorldThreeAsyncCalls_whenComplete_success_when_2_exception() {
        //given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("runtime exception"));
        when(helloWorldService.world()).thenThrow(new RuntimeException("runtime exception"));
        //when
        String result = cfhwe.helloWorldThreeAsyncCalls_whenComplete(); // "hello world! HI COMPLETABLEFUTURE!"
        //then
        assertEquals(" HI COMPLETABLEFUTURE!", result);
    }
}