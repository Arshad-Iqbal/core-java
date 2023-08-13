package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListSpliteratorExampleTest {

    LinkedListSpliteratorExample linkedListSpliteratorExample = new LinkedListSpliteratorExample();

    @RepeatedTest(5)
    void multiplyEachValue_with_stream() {
        // Gievn
        int size = 1000000;
        LinkedList<Integer> inputList = DataSet.generateIntegerLinkedList(size);
        // When
        List<Integer> resultList = linkedListSpliteratorExample.multiplyEachValue(inputList, 2, false);
        // Then
        assertEquals(size, resultList.size());
    }

    @RepeatedTest(5)
    void multiplyEachValue_with_parallel_stream() {
        // Gievn
        int size = 1000000;
        LinkedList<Integer> inputList = DataSet.generateIntegerLinkedList(size);
        // When
        List<Integer> resultList = linkedListSpliteratorExample.multiplyEachValue(inputList, 2, true);
        // Then
        assertEquals(size, resultList.size());
    }
}