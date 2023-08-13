package com.selflearnJava.functionalInterfaces;

import java.util.function.Predicate;

public class PredicateExample {
    static Predicate<Integer> p1 = t -> t%2 == 0;
    static Predicate<Integer> p2 = t -> t%2 != 0;

    public static void predicateAnd() {
        System.out.println("Predicate And :: "+ (p1.and(p2).test(13))); //predicate chaining
    }
    public static void predicateOr() {
        System.out.println("Predicate Or :: "+ p1.or(p2).test(13)); //predicate chaining
    }

    public static void predicateNegate() {
        System.out.println("Predicate Negate :: "+ p1.or(p2).negate().test(13)); //predicate chaining
    }

    public static void main(String[] args) {
        System.out.println(p1.test(10));
        predicateAnd();
        predicateOr();
        predicateNegate();
    }
}
