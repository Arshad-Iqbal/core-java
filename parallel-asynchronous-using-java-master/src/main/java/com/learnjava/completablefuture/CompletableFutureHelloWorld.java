package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class CompletableFutureHelloWorld {

    private HelloWorldService helloWorldService;

    public CompletableFutureHelloWorld(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    public CompletableFuture<String> helloWorld() {
        return CompletableFuture.supplyAsync(()->helloWorldService.helloWorld())
                .thenApply(String::toUpperCase);
    }

    public CompletableFuture<String> helloWorld_withSize() {
        return CompletableFuture.supplyAsync(()->helloWorldService.helloWorld())
                .thenApply(String::toUpperCase)
                .thenApply(s -> {
                    String res = s.length() + " - " + s;
                    return res;
                });
    }

    public String helloWorldMultipleAsyncCalls(){
        startTimer();
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> helloWorldService.hello());
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(() -> helloWorldService.world());
        String res = helloFuture
                .thenCombine(worldFuture, (helloResult, worldResult) -> helloResult + worldResult)
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();
        return res;
    }

    public String helloWorldThreeAsyncCalls() {
        startTimer();
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> helloWorldService.hello());
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(() -> helloWorldService.world());
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String res = helloFuture
                .thenCombine(worldFuture, (helloResult, worldResult) -> helloResult + worldResult)
                .thenCombine(completableFuture, (firstResult, secondResult) -> firstResult + secondResult)
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();
        return res;
    }

    public String helloWorldThreeAsyncCalls_log() {
        startTimer();
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> helloWorldService.hello());
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(() -> helloWorldService.world());
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String res = helloFuture
                .thenCombine(worldFuture, (helloResult, worldResult) -> {
                    log("thenCombine worldFuture");
                    return helloResult + worldResult;
                })
                .thenCombine(completableFuture, (firstResult, secondResult) -> {
                    log("thenCombine completableFuture");
                    return firstResult + secondResult;
                })
                .thenApply( s -> {
                    log(" thenApply toUpperCase ");
                    return s.toUpperCase();
                })
                .join();
        timeTaken();
        return res;
    }

    public String helloWorldThreeAsyncCalls_custom_thread_pool() {
        startTimer();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> helloWorldService.hello(), executorService);
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(() -> helloWorldService.world(), executorService);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        }, executorService);

        String res = helloFuture
                .thenCombine(worldFuture, (helloResult, worldResult) -> {
                    log("thenCombine worldFuture");
                    return helloResult + worldResult;
                })
                .thenCombine(completableFuture, (firstResult, secondResult) -> {
                    log("thenCombine completableFuture");
                    return firstResult + secondResult;
                })
                .thenApply( s -> {
                    log(" thenApply toUpperCase ");
                    return s.toUpperCase();
                })
                .join();
        timeTaken();
        return res;
    }

    public String helloWorldThreeAsyncCalls_custom_thread_pool_async() {
        startTimer();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> helloWorldService.hello(), executorService);
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(() -> helloWorldService.world(), executorService);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        }, executorService);

        String res = helloFuture
                .thenCombineAsync(worldFuture, (helloResult, worldResult) -> {
                    log("thenCombine worldFuture");
                    return helloResult + worldResult;
                }, executorService)
                .thenCombineAsync(completableFuture, (firstResult, secondResult) -> {
                    log("thenCombine completableFuture");
                    return firstResult + secondResult;
                }, executorService)
                .thenApplyAsync( s -> {
                    log(" thenApply toUpperCase ");
                    return s.toUpperCase();
                }, executorService)
                .join();
        timeTaken();
        return res;
    }
    public String helloWorldThreeAsyncCalls_log_async() {
        startTimer();
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> helloWorldService.hello());
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(() -> helloWorldService.world());
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String res = helloFuture
                .thenCombineAsync(worldFuture, (helloResult, worldResult) -> {
                    log("thenCombine worldFuture");
                    return helloResult + worldResult;
                })
                .thenCombineAsync(completableFuture, (firstResult, secondResult) -> {
                    log("thenCombine completableFuture");
                    return firstResult + secondResult;
                })
                .thenApplyAsync( s -> {
                    log(" thenApply toUpperCase ");
                    return s.toUpperCase();
                })
                .join();
        timeTaken();
        return res;
    }

    public String helloWorld_4_async_calls() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> helloWorldService.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> helloWorldService.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " HI CompletableFuture!";
        });
        CompletableFuture<String> byeCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Bye!";
        });

        String hw = hello
                .thenCombine(world, (h, w) -> h + w) // (first,second)
                .thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
                .thenCombine(byeCompletableFuture, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();

        return hw;
    }

    public CompletableFuture<String> helloWorldThenCompose() {
        return CompletableFuture.supplyAsync(() -> helloWorldService.hello())
                .thenCompose((previous) -> helloWorldService.worldFuture(previous))
                .thenApply(String::toUpperCase);
    }

    public static void main(String[] args) {
        HelloWorldService helloWorldService = new HelloWorldService();

//        CompletableFuture.supplyAsync(() -> helloWorldService.helloWorld()) // runs this in a common fork join pool
//                .thenAccept((result) -> { // result is passed to the thenAccept method in the form of event.
//                    log("Result is : "+ result);
//                }).join(); // blocks main thread.

        CompletableFuture.supplyAsync(() -> helloWorldService.helloWorld())
                        .thenApply(String::toUpperCase)
                                .thenAccept((result) -> {
                                    log("Result is : "+ result);
                                }).join();
        log("Done!");
    }
}
