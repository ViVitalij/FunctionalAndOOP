package sda;

import sda.workers.AbstractEmployee;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by m.losK on 2017-02-18.
 */
public class Application {
    public static void main(String[] args) {
        List<AbstractEmployee> employeeList = new ArrayList<>();
        employeeList.add(new AbstractEmployee("Mateusz", "Loska", 10800, "JAVA"));
        employeeList.add(new AbstractEmployee("Anna", "Loska", 8000, "Director"));
        employeeList.add(new AbstractEmployee("Piotr", "Cienkowski", 10000, "PM"));
        employeeList.add(new AbstractEmployee("Pawel", "Stepniak", 4200, "HR"));
        employeeList.add(new AbstractEmployee("Lukasz", "Stepniak", 10800, "JAVA"));

        //1. using filter, display people from department JAVA only
        employeeList.stream()
                .filter(e -> e.getDepartment().equals("JAVA"))
                .forEach(e -> System.out.println(e));

        System.out.println();

        //2. display women only (female names in Polish mostly end with a letter a)
        employeeList.stream()
                .filter(e -> e.getFirstName().endsWith("a"))
                .forEach(e -> System.out.println(e));

        System.out.println();

        //3. display people whose salary is higher than 5000
        employeeList.stream()
                .filter(e -> e.getSalary() > 5000)
                .forEach(e -> System.out.println(e));

        System.out.println();

        //4. display people whose salary is higher than 5000 and belongs to department JAVA
        employeeList.stream()
                .filter(e -> e.getDepartment().equals("JAVA"))
                .filter(e -> e.getSalary() > 5000)
                .forEach(e -> System.out.println(e));

        System.out.println();

        //5. display list of people from department JAVA only
        List<AbstractEmployee> javaEmployees = employeeList.stream()
                .filter(e -> e.getDepartment().equals("JAVA"))
                .collect(Collectors.toList());
        System.out.println(javaEmployees);

        System.out.println();

        //6. search by last name
        employeeList.stream()
                .filter(e -> e.getLastName().equals("Loska"))
                .forEach(e -> System.out.println(e));

        System.out.println();

        //7. search by last name using startsWith method
        employeeList.stream()
                .filter(e -> e.getLastName().startsWith("S"))
                .forEach(e -> System.out.println(e));

        System.out.println();

        //8. divide a list of people on the map where the key is their name, and value is AbstractEmployee
        Map<String, AbstractEmployee> map = employeeList.stream()
                .collect(Collectors.toMap(e -> e.getFirstName(), e -> e));  //key = name. and value = itself

        map.forEach((k, v) -> System.out.println(k + " " + v));

        System.out.println();

        //9. search employee by name + " " + last name
        employeeList.stream()
                .filter(e -> (e.getFirstName() + " " + e.getLastName()).equals("Mateusz Loska"))
                .forEach(e -> System.out.println(e));

        System.out.println();

        //10. sort by salary
        employeeList.sort((e1, e2) ->                           //comparator
                e1.getSalary() > e2.getSalary() ? 1 :
                        e1.getSalary() == e2.getSalary() ? 0 : -1);
        employeeList.forEach(e -> System.out.println(e.getFirstName() + ": " + e.getSalary()));

        System.out.println();

        //11. display employee with the highest salary (attention: display only 1 person even if there is more with equal salary)
        employeeList.sort((e1, e2) -> e1.getSalary() < e2.getSalary() ? 1 : -1);
        System.out.println(employeeList.get(0));

        //or better (attention: display only 1 person [another than lambda above] even if there is more with equal salary)
        AbstractEmployee richestEmployee = employeeList.stream()
                .max((e1, e2) -> e1.getSalary() > e2.getSalary() ? 1 : -1)
                .get();
        System.out.println(richestEmployee);

        System.out.println();

        //12. display the poorest employee
        AbstractEmployee poorestEmployee = employeeList.stream()
                .min((e1, e2) -> e1.getSalary() > e2.getSalary() ? 1 : -1)
                .get();
        System.out.println(poorestEmployee);

        System.out.println();
        System.out.println();

        //13. display list of employees with salary higher than 3000 according to map which the key is department [see mehtod below]
        Map<String, List<AbstractEmployee>> map1 = listToMap(employeeList);
        List<AbstractEmployee> tmpList = new ArrayList<>();
        map1.entrySet().stream()
                .forEach(e -> {
                    List<AbstractEmployee> value = e.getValue();
                    value.stream()
                            .filter(e1 -> e1.getSalary() > 3000)
                            .forEach(e1 -> tmpList.add(e1));
                });
        System.out.println(tmpList);


    }

    public static Map<String, List<AbstractEmployee>> listToMap(List<AbstractEmployee> employyes) {
        Map<String, List<AbstractEmployee>> map = new HashMap<>();
        for (AbstractEmployee employee : employyes) {
            if (map.containsKey(employee.getDepartment())) {
                List<AbstractEmployee> listOfEmployees = map.get(employee.getDepartment());
                listOfEmployees.add(employee);
            } else {
                ArrayList<AbstractEmployee> lisOfEmployees = new ArrayList<>();
                lisOfEmployees.add(employee);
                map.put(employee.getDepartment(), lisOfEmployees);
            }
        }
        return map;
    }

}
