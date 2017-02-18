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

        employeeList.stream()
                .filter(e -> e.getDepartment().equals("JAVA"))
                .forEach(e -> System.out.println(e));

        System.out.println();

        employeeList.stream()
                .filter(e -> e.getFirstName().endsWith("a"))
                .forEach(e -> System.out.println(e));

        System.out.println();

        employeeList.stream()
                .filter(e -> e.getSalary() > 5000)
                .forEach(e -> System.out.println(e));

        System.out.println();

        employeeList.stream()
                .filter(e -> e.getDepartment().equals("JAVA"))
                .filter(e -> e.getSalary() > 5000)
                .forEach(e -> System.out.println(e));

        System.out.println();

        List<AbstractEmployee> javaEmployees = employeeList.stream()
                .filter(e -> e.getDepartment().equals("JAVA"))
                .collect(Collectors.toList());
        System.out.println(javaEmployees);

        System.out.println();

        employeeList.stream()
                .filter(e -> e.getLastName().equals("Loska"))
                .forEach(e -> System.out.println(e));

        System.out.println();

        employeeList.stream()
                .filter(e -> e.getLastName().startsWith("S"))
                .forEach(e -> System.out.println(e));

        System.out.println();

        Map<String, AbstractEmployee> map = employeeList.stream()
                .collect(Collectors.toMap(e -> e.getFirstName(), e -> e));  //key = name. and value = itself

        map.forEach((k, v) -> System.out.println(k + " " + v));

        System.out.println();

        employeeList.stream()
                .filter(e -> (e.getFirstName() + " " + e.getLastName()).equals("Mateusz Loska"))
                .forEach(e -> System.out.println(e));

        System.out.println();

        employeeList.sort((e1, e2) -> e1.getSalary() > e2.getSalary() ? 1 : -1);    //comparator
        employeeList.forEach(e -> System.out.println(e.getFirstName() + ": " + e.getSalary()));

        System.out.println();


        employeeList.sort((e1, e2) -> e1.getSalary() < e2.getSalary() ? 1 : -1);
        System.out.println(employeeList.get(0));

        //or better

        AbstractEmployee richestEmployee = employeeList.stream()
                .max((e1, e2) -> e1.getSalary() > e2.getSalary() ? 1 : -1)
                .get();
        System.out.println(richestEmployee);

        System.out.println();

        AbstractEmployee poorestEmployee = employeeList.stream()
                .min((e1, e2) -> e1.getSalary() > e2.getSalary() ? 1 : -1)
                .get();
        System.out.println(poorestEmployee);

        System.out.println();
        System.out.println();

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
