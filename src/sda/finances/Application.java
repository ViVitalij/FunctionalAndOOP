package sda.finances;

import sda.finances.model.Expense;
import sda.finances.model.Product;

import java.util.*;

/**
 * Created by m.losK on 2017-02-20.
 */
public class Application {
    public static void main(String[] args) {
        List<Expense> expenses = init();


        //1. display all the goods whose unit price is less than 3
        expenses.forEach(expense -> {
            expense.getProducts().stream()
                    .filter(product -> product.getUnitPrice() < 3)
                    .forEach(product -> System.out.println(product));
        });

        System.out.println();

        //2. show only grocery products whose unit price is less than 3
        expenses.stream()
                .filter(e -> e.getType().equals("groceries"))
                .forEach(expense -> {
                    expense.getProducts().stream()
                            .filter(product -> product.getUnitPrice() <= 3)
                            .forEach(product -> System.out.println(product));
                });

        System.out.println();

        //3. show only bananas orders
        expenses.forEach(expense ->
                expense.getProducts().stream()
                        .filter(product -> product.getName().equals("banana"))
                        .forEach(product -> System.out.println(product)));

        System.out.println();


        //4. show total price of all foodstuffs
        List<Double> productsPrice = new ArrayList<>();
        expenses.stream()
                .filter(expense -> expense.getType().equals("groceries"))
                .forEach(groceries -> {
                    groceries.getProducts().stream()
                            .forEach(product -> productsPrice.add(product.getUnitPrice() * product.getAmount()));
                });
        double totalPrice = productsPrice.stream().mapToDouble(d -> d).sum();
        System.out.println("Total price is: " + totalPrice);
    }

    private static List<Expense> init() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("banana", 5, 2));
        products.add(new Product("apple", 2, 4));
        products.add(new Product("orange", 4, 5));
        products.add(new Product("pineapple", 5, 1));
        Expense expense = new Expense("groceries", products);

        List<Product> products2 = new ArrayList<>();
        products2.add(new Product("paint", 1, 25));
        products2.add(new Product("hammer", 2, 10));
        products2.add(new Product("nails", 3, 15));
        Expense expense2 = new Expense("building materials", products2, 2017, 2, 19);

        List<Product> products3 = new ArrayList<>();
        products3.add(new Product("vitamin C", 2, 3));
        products3.add(new Product("painkiller", 2, 4));
        products3.add(new Product("cough syrup", 3, 1));
        Expense expense3 = new Expense("medicine", products3, 2017, 2, 18);

        List<Product> products4 = new ArrayList<>();
        products4.add(new Product("banana", 2, 1.5));
        products4.add(new Product("bread", 1, 4));
        products4.add(new Product("milk", 3, 1.75));
        Expense expense4 = new Expense("groceries", products4, 2017, 2, 17);

        return Arrays.asList(expense, expense2, expense3, expense4);
    }
}