package main;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        http://prologistic.com.ua/polnoe-rukovodstvo-po-java-8-stream.html
	    new Main().run();
    }

    private void run() {
        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("Иван", 25, 1.6, "Nikolaev",'m'),
                new Person("Иван", 19, 1.3, "Kiev",'m'),
                new Person("Инна", 22, 1.7, "Kiev",'f'),
                new Person("Николай", 24, 2.1, "Dnepr",'m'),
                new Person("Анна", 35, 2.6, "Odessa",'f'),
                new Person("Светлана", 30, 5.6, "Odessa",'f'),
                new Person("Мария", 20, 1.6, "Nikolaev",'f')

        ));

        Scanner scanner = new Scanner(System.in);
        int m = 0;
        menu:
        do {
            System.out.println("1. Весь список");
            System.out.println("2. только мужчины");
            System.out.println("3. только женщины");
            System.out.println("4. только взрослые");
            System.out.println("5. только взрослые мужчины");
            System.out.println("6. Сначала женщины, потом мужчины");
            System.out.println("7. По городам");
            System.out.println("8. Sorted");
            System.out.println("0. Выход");
            m = scanner.nextInt();
            switch (m) {
                case 1: showAll(persons); break;
                case 2: showMen(persons); break;
                case 3: showWomen(persons); break;
                case 4: showAdults(persons); break;
                case 5: showAdultsMen(persons); break;
                case 6: showInSexOrder(persons); break;
                case 7: showByCity(persons); break;
                case 8: showByAlphaOrder(persons); break;
                case 0: break menu;
            }
            System.out.println("----------------");
        } while (m != 0);

    }

    private void showByAlphaOrder(List<Person> persons) {
//        persons.stream()
//                .sorted((p1,p2)->p1.getName().compareTo(p2.getName()))
//                .forEach(System.out::println);
        persons.stream()
                .sorted(Comparator.comparing(Person::getName).thenComparing(Person::getAge).reversed())
                .forEach(System.out::println);
    }

    private void showByCity(List<Person> persons) {
        Map<String, List<Person>> personsByCity = persons.stream().collect(Collectors.groupingBy(p -> p.getCity()));
        personsByCity.entrySet()
                .forEach(e->{
                    System.out.println(e.getKey());
                    e.getValue().forEach(p -> System.out.println("   " + p));
                });
    }

    private void showInSexOrder(List<Person> persons) {
        Map<Boolean, List<Person>> personsBySex = persons.stream().collect(Collectors.partitioningBy(p -> p.getSex() == 'f'));
        personsBySex.entrySet().forEach(e-> e.getValue().forEach(System.out::println));
    }

    private void showAdultsMen(List<Person> persons) {
        showAllWithFilter(persons, ((Predicate<Person>)(Person p) -> p.getAge()>=21).and(p->p.getSex()=='m'));
    }

    private void showAdults(List<Person> persons) {
        showAllWithFilter(persons, p->p.getAge()>=21);
    }

    private void showAllWithFilter(List<Person> persons, Predicate<Person> predicate) {
        persons.stream().filter(predicate).forEach(p-> System.out.println(p));
    }

    private void showWomen(List<Person> persons) {
        showAllWithFilter(persons, p -> p.getSex()!='m');
    }

    private void showMen(List<Person> persons) {
        showAllWithFilter(persons, p->p.getSex()=='m');
    }

    private void showAll(List<Person> persons) {
        showAllWithFilter(persons, p->true);
        //persons.stream().forEach(System.out::println);
    }
}
