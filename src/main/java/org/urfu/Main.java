package org.urfu;

import org.urfu.practice2.Task1;
import org.urfu.practice2.Task2;
import org.urfu.practice2.Task3.Employee;
import org.urfu.practice2.Task3.Task3;
import org.urfu.practice2.Task4;
import org.urfu.practice2.Task5.Task;
import org.urfu.practice2.Task5.Task5;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Task1");
        int n1 = scanner.nextInt();
        Task1.startTask(n1);

        System.out.println("\nTask2");
        int n2 = scanner.nextInt();
        Task2.startTask(n2);

        System.out.println("\nTask3");
        var employeeList = new ArrayList<Employee>() {{
            add(new Employee("John", 27, "dep1", 3000d));
            add(new Employee("Mark", 22, "dep2", 3000d));
            add(new Employee("Stacy", 28, "dep1", 4000d));
            add(new Employee("Dick", 35, "dep3", 5000d));
            add(new Employee("Amelie", 23, "dep2", 3500d));
            add(new Employee("Rachel", 31, "dep1", 5000d));
        }};
        Task3.startTask(employeeList, "dep1");

        System.out.println("\nTask4");
        Task4.startTask();

        System.out.println("\nTask5");
        String fileURL = "https://filesamples.com/samples/video/mp4/sample_1280x720_surfing_with_audio.mp4";
        String savePath = Paths.get(System.getProperty("user.home"), "file").toString();
        Task task = new Task5(fileURL, savePath);
        task.start();
        Thread.sleep(500);
        task.stop();
    }
}