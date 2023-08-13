package com.selflearnJava.functionalInterfaces;

import com.selflearnJava.data.Student;
import com.selflearnJava.data.StudentDataBase;

import java.util.List;
import java.util.function.Consumer;

public class ConsumerExample {
    static List<Student> students = StudentDataBase.getAllStudents();
    static
    Consumer<Student> nameConsumer = (student) -> System.out.print(student.getName());
    static
    Consumer<Student> activityConsumer = (student) -> System.out.println(student.getActivities());

    public static void main(String[] args) {

        Consumer<String> consumer = (t) -> System.out.println(t.toUpperCase());

        consumer.accept("java8");

        printNameAndActivities();
        printNameAndActivitiesWithCondition();

    }

    public static void printStudent(){
        List<Student> students = StudentDataBase.getAllStudents();
        Consumer<Student> studentConsumer = (student) -> System.out.println(student);
        students.forEach(studentConsumer);
    }
    public static void printNameAndActivities(){
        System.out.println("printNameAndActivities : ");
        students.forEach(nameConsumer.andThen(activityConsumer)); // Consumer chaining

    }

    public static void printNameAndActivitiesWithCondition(){
        System.out.println("printNameAndActivitiesWithCondition : ");
        students.forEach(student -> {
            if(student.getGradeLevel() >= 3 && student.getGpa() >= 3.9){
                nameConsumer.andThen(activityConsumer).accept(student);
            }
        });

    }
}
