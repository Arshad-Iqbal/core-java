package com.learnjava.forkjoin;

import com.learnjava.util.DataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import static com.learnjava.util.CommonUtil.delay;
import static com.learnjava.util.CommonUtil.stopWatch;
import static com.learnjava.util.LoggerUtil.log;

public class ForkJoinUsingRecursion extends RecursiveTask<List<String>> {

    private List<String> inputList;

    public ForkJoinUsingRecursion(List<String> inputList) {
        this.inputList = inputList;
    }

    public static void main(String[] args) {

        stopWatch.start();
        List<String> resultList = new ArrayList<>();
        List<String> names = DataSet.namesList();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinUsingRecursion forkJoinUsingRecursion = new ForkJoinUsingRecursion(names);
        resultList = forkJoinPool.invoke(forkJoinUsingRecursion);// The actual tasks gets added to the shared queue.
// Once the task is added to the shared queue, all the Threads in the fork join pool start take the task
        // and start working on it.
        stopWatch.stop();
        log("Final Result : "+ resultList);
        log("Total Time Taken : "+ stopWatch.getTime());
    }


    private static String addNameLengthTransform(String name) {
        delay(500);
        return name.length()+" - "+name ;
    }

    /**
     * Recursively split the list and runs each half as a ForkJoinTask
     * Right way of using Fork/Join Task
     */
    @Override
    protected List<String> compute() {
        if (this.inputList.size() <= 1) {
            List<String> resultList = new ArrayList<>();
            inputList.forEach(name -> resultList.add(transform(name)));
            return resultList;
        }
        int midPoint = inputList.size() / 2;
        ForkJoinTask<List<String>> leftInputList = new ForkJoinUsingRecursion(inputList.subList(0, midPoint)) //left side of the list
                .fork(); // 1. asynchronously arranges this task in the deque,
        inputList = inputList.subList(midPoint, inputList.size()); //right side of the list
        List<String> rightResult = compute();
        List<String> leftResult = leftInputList.join();
        //log("leftResult : "+ leftResult);
        leftResult.addAll(rightResult);
        return leftResult;
    }

    private String transform(String name) {
        delay(500);
        return name.length() + " - " + name;
    }
}
