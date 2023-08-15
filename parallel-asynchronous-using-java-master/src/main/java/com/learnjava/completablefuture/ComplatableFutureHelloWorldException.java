package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class ComplatableFutureHelloWorldException {

    private HelloWorldService helloWorldService;

    public ComplatableFutureHelloWorldException(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    public String helloWorldThreeAsyncCalls_handle() {
        startTimer();
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> helloWorldService.hello());
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(() -> helloWorldService.world());
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String res = helloFuture
                .handle((result, exception) -> { // handle gets invoked even if there is no exception
                    log("result: "+result);
                    if (exception!= null) {
                        log("helloFuture: got exception : "+ exception.getMessage());
                        return "*";
                    }else
                        return result;

                })
                .thenCombine(worldFuture, (helloResult, worldResult) -> helloResult + worldResult) // "exception world!"
                .handle((result, exception) -> {
                    log("result: "+result);
                    if (exception!= null) {
                        log("worldFuture: got exception : "+ exception.getMessage());
                        return "*";
                    }else
                        return result;
                })
                .thenCombine(completableFuture, (firstResult, secondResult) -> firstResult + secondResult) //"recoverable value! world! Hi CompletableFuture!"
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();
        return res;
    }

    public String helloWorldThreeAsyncCalls_exceptionally() {
        startTimer();
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> helloWorldService.hello());
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(() -> helloWorldService.world());
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String res = helloFuture
                .exceptionally((exception) -> { // exceptionally only gets invoked if there is any exception
                    log("helloFuture: got exception : "+ exception.getMessage());
                    return "*";
                })
                .thenCombine(worldFuture, (helloResult, worldResult) -> helloResult + worldResult) // "exception world!"
                .exceptionally((exception) -> {
                    log("worldFuture: got exception : "+ exception.getMessage());
                    return "*";
                })
                .thenCombine(completableFuture, (firstResult, secondResult) -> firstResult + secondResult) //"recoverable value! world! Hi CompletableFuture!"
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();
        return res;
    }

    public String helloWorldThreeAsyncCalls_whenComplete() {
        startTimer();
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> helloWorldService.hello());
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(() -> helloWorldService.world());
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String res = helloFuture
                .whenComplete((result, exception) -> { // handle gets invoked even if there is no exception
                    log("result: "+result);
                    if (exception!= null) {
                        log("helloFuture: got exception : "+ exception.getMessage());
                    }
                })
                .thenCombine(worldFuture, (helloResult, worldResult) -> helloResult + worldResult) // "exception world!"
                .whenComplete((result, exception) -> {
                    log("result: "+result);
                    if (exception!= null) {
                        log("worldFuture: got exception : " + exception.getMessage());
                    }
                })
                .exceptionally((exception) -> {
                    log("whenComplete2: got exception : "+ exception.getMessage());
                    return "";
                })
                .thenCombine(completableFuture, (firstResult, secondResult) -> firstResult + secondResult) //"recoverable value! world! Hi CompletableFuture!"
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();
        return res;
    }
}
