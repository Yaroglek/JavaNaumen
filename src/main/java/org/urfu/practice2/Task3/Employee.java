package org.urfu.practice2.Task3;

public class Employee {
    private String fullName;
    private Integer age;
    private String department;
    private Double salary;

    public Employee() {
    }

    public Employee(String fullName, Integer age, String department, Double salary) {
        this.fullName = fullName;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getFullName() {
        return this.fullName;
    }

    public Integer getAge() {
        return this.age;
    }

    public String getDepartment() {
        return this.department;
    }

    public Double getSalary() {
        return this.salary;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String toString() {
        return "Task3.Employee(fullName=" + this.getFullName() + ", age=" + this.getAge() + ", department=" + this.getDepartment() + ", salary=" + this.getSalary() + ")";
    }
}