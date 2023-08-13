package com.selflearnJava.functionalInterfaces;

import com.selflearnJava.data.Student;
import com.selflearnJava.data.StudentDataBase;

import java.util.List;
import java.util.function.BiConsumer;

public class BiConsumerExample {

    public static void nameAndActivitiesBiConsumer(){
        BiConsumer<String, List<String>> biconsumer = (name, activities)
                -> System.out.println("Name : "+name+" \t Activities : "+activities);
        List<Student> allStudents = StudentDataBase.getAllStudents();
        allStudents.forEach( student ->
                biconsumer.accept(student.getName(), student.getActivities())
        );
    }

    public static void main(String[] args) {
        BiConsumer<String, String> biConsumer = (a,b) -> System.out.println("First = "+a+"\tSecond = "+b);
        biConsumer.accept("arshad","iqbal");

        BiConsumer<Integer, Integer> multiply = (a,b) -> System.out.println("Multiply = "+(a * b));
        BiConsumer<Integer, Integer> division = (a,b) -> System.out.println("Division = "+(a / b));

        multiply.andThen(division).accept(10,5);
        nameAndActivitiesBiConsumer();
    }
}
