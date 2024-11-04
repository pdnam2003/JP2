package E2.Entity;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String name;
    private Department department;
    private Gender gender;
    private LocalDate dob;
    public Employee() {
    }

    public Employee(int id, String name, Department department,Gender gender, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.gender = gender;
        this.dob= dob;
    }
    public Employee( String name,int id){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", gender=" + gender +
                ", dob=" + dob +
                '}';
    }
}
