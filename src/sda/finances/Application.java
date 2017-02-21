package sda.finances;

import sda.finances.model.Expense;
import sda.finances.model.Product;

import java.time.LocalDate;
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

        //3. show total price of bananas
        double bananas = expenses.stream()
                .mapToDouble(expense -> expense.getProducts().stream()
                        .filter(product -> product.getName().equals("banana"))
                        .mapToDouble(product -> product.getUnitPrice() * product.getAmount()).sum())
                .sum();
        System.out.println(bananas);

        System.out.println();

        //4. show total price of all foodstuffs
        double totalPrice = expenses.stream()
                .filter(expense -> expense.getType().equals("groceries"))
                .mapToDouble(groceries -> groceries.getPrice()).sum();
        System.out.println("Total price is: " + totalPrice);

        System.out.println();

        //5. show products bought before 19 of February
        expenses.stream()
                .filter(expense -> expense.getDate().isBefore(LocalDate.of(2017, 02, 19)))
                .forEach(expense -> expense.getProducts()
                        .forEach(product -> System.out.println(product)));

        System.out.println();

        //6. display expenses for a specific day
        double totalPriceForToday = expenses.stream()
                .filter(expense -> expense.getDate().isEqual(LocalDate.of(2017, 02, 21)))
                .mapToDouble(Expense::getPrice).sum();

        System.out.println(totalPriceForToday);

        System.out.println();

        //7. show total price of products start with letter p
        double priceOfProductsStartingWithLetterP = expenses.stream()
                .mapToDouble(expense -> expense.getProducts().stream()
                        .filter(product -> product.getName().startsWith("p"))
                        .mapToDouble(product -> product.getUnitPrice() * product.getAmount()).sum())
                .sum();
        System.out.println(priceOfProductsStartingWithLetterP);

        System.out.println();

        //8. display expenses spent for a beer in a specific day
        double moneySpentForABeer = expenses.stream()
                .filter(expense -> expense.getDate().isEqual(LocalDate.of(2017, 02, 17)))
                .mapToDouble(expense -> expense.getProducts()
                        .stream()
                        .filter(product -> product.getName().equals("beer"))
                        .mapToDouble(product -> product.getUnitPrice() * product.getAmount())
                        .sum())
                .sum();

        System.out.println(moneySpentForABeer);

        System.out.println();

        //9. show cost of groceries which we bought in even quantities
        System.out.println(expenses.stream()
                .filter(expense -> expense.getType().equals("groceries"))
                .mapToDouble(expense -> expense.getProducts().stream()
                        .filter(product -> product.getAmount() % 2 == 0)
                        .mapToDouble(product -> product.getAmount() * product.getUnitPrice()).sum())
                .sum());

        System.out.println();

        //10. from every expense display the most expensive product
        expenses.stream()
                .map(expense ->
                        expense.getProducts().stream()
                                .max((e1, e2) -> (e1.getUnitPrice() * e1.getAmount()) > e2.getUnitPrice() * e2.getAmount()
                                        ? 1 : -1).get())                        //here get() return specific object
                .forEach(product -> System.out.println(product));

        System.out.println();

        //11. display the most expensive product (here: from list of the most expensive products from every expense)
        System.out.println(expenses.stream()
                .map(expense ->
                        expense.getProducts().stream()
                                .max((e1, e2) -> (e1.getUnitPrice() * e1.getAmount()) > e2.getUnitPrice() * e2.getAmount()
                                        ? 1 : -1).get())
                .max((e1, e2) -> (e1.getUnitPrice() * e1.getAmount()) > e2.getUnitPrice() * e2.getAmount()
                        ? 1 : -1).get());
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
        products4.add(new Product("beer", 1, 4));
        products4.add(new Product("milk", 3, 1.75));
        Expense expense4 = new Expense("groceries", products4, 2017, 2, 17);

        return Arrays.asList(expense, expense2, expense3, expense4);
    }
}