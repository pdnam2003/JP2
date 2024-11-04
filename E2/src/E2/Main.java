package E2;

import E2.Entity.Department;
import E2.Entity.Employee;
import E2.Entity.Gender;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    static List<Employee> employees = new ArrayList<Employee>(List.of(
            new Employee("ng1",34),
            new Employee("ng2",22),
            new Employee("ng3",35),
            new Employee("ng4",14),
            new Employee("ng5",53),
            new Employee("ng6",51)

    ));
    public static void main(String[] args) {
        List<Department> departments = new ArrayList<Department>();
        List<Employee> employees = new ArrayList<Employee>();

        departments.add(new Department(1, "HR", "Human Resource"));
        departments.add(new Department(2, "IT", "Information Technology"));

        employees.add(new Employee(1, "Yugi", departments.get(0), Gender.FEMALE, LocalDate.of(2001, 10, 1)));
        employees.add(new Employee(2, "Yugi", departments.get(1), Gender.FEMALE, LocalDate.of(2001, 1, 1)));
        employees.add(new Employee(3, "Mutou", departments.get(0), Gender.MALE, LocalDate.of(2003, 2, 2)));
//
//        Map<String, Long> countEmployees = new HashMap<>();
//
//        //C1
//        departments.stream().forEach(department -> {
//            long totalEmp = employees.stream()
//                    .filter(emp -> department.getId() == emp.getDepartment().getId())
//                    .collect(Collectors.toSet())
//                    .stream().count();
//            countEmployees.put(department.getCode(), totalEmp);
//        });
//        System.out.println(countEmployees);
//        //C2
//        departments.forEach(d -> {
//            employees.stream()
//                    .filter(emp -> emp.getDepartment().getId() == d.getId())
//                    .map(employee -> {
//                        return countEmployees.put(d.getCode(), countEmployees.getOrDefault(d.getCode(), 0L) + 1);
//                    });
//        });
//        System.out.println(countEmployees);
//        //C3
//        departments.stream().forEach(department -> {
//            Set<Employee> employeeSet = employees.stream()
//                    .filter(employee -> employee.getDepartment().getId() == department.getId())
//                    .collect(Collectors.toSet());
//            countEmployees.put(department.getCode(), (long) employeeSet.size());
//        });
//        System.out.println(countEmployees);
//        //count employees by GenderMale
//        Map<String, Long> countMaleEmployees = new HashMap<>();
//        departments.forEach(department -> {
//            long maleEmpCount = employees.stream()
//                    .filter(emp -> emp.getDepartment().getId() == department.getId() &&
//                            emp.getGender() == Gender.MALE)
//                    .count();
//            countMaleEmployees.put(department.getCode(), maleEmpCount);
//        });
//        System.out.println(countMaleEmployees);
//        //count emp dob
//        Map<String, Set<Employee>> employeeDoB = new HashMap<>();
//        int currentMonth = LocalDate.now().getMonthValue();
//        departments.forEach(department -> {
//            Set<Employee> dobEmp = employees.stream()
//                    .filter(emp -> emp.getDepartment().getId() == department.getId() &&
//                            emp.getDob().getMonthValue() == currentMonth)
//                    .collect(Collectors.toSet());
//            employeeDoB.put(department.getCode(), dobEmp);
//        });
//        System.out.println(employeeDoB);

//        Map<Department, Set<Employee>> employeeMap = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.toSet()));
//                employees.forEach(Department , Set<Employee>List)->System.out.println(key->employees )
//
              List<String> empNames= employees.stream()
                      .collect(Collectors.mapping(Employee::getName,Collectors.toList()));
        System.out.println("List of emp names"+empNames);


    }
}
