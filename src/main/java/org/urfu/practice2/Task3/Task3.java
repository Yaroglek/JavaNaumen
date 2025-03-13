package org.urfu.practice2.Task3;

import java.util.List;
import java.util.stream.Collectors;

public final class Task3 {
    private Task3() {
    }

    public static void startTask(List<Employee> employeeList, String dep) {
        for (var employee : employeeList) {
            System.out.println(employee.toString());
        }
        System.out.println(getAvgSalaryByDep(employeeList, dep));
    }

    private static Double getAvgSalaryByDep(List<Employee> employees, String department) {
        return employees.stream()
                .filter(e -> e.getDepartment().equals(department))
                .collect(Collectors.averagingDouble(Employee::getSalary));
    }
}
