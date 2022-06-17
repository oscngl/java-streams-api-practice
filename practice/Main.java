package practice;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Person> people = getPeople();

        // Filter
        List<Person> females = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());

        females.forEach(System.out::println);

        // Sort
        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
                .collect(Collectors.toList());

        sorted.forEach(System.out::println);

        // All Match
        boolean allMatch = people.stream()
                .allMatch(person -> person.getAge() > 18);

        System.out.println(allMatch);

        // Any Match
        boolean anyMatch = people.stream()
                .anyMatch(person -> person.getAge() > 50);

        System.out.println(anyMatch);

        // None Match
        boolean noneMatch = people.stream()
                .noneMatch(person -> person.getName().equals("Ömer"));

        System.out.println(noneMatch);

        // Max
        people.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        // Min
        people.stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        // Group
        Map<Gender, List<Person>> groupByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        groupByGender.forEach(((gender, person) -> {
            System.out.println(gender);
            person.forEach(System.out::println);
            System.out.println();
        }));

        // Find First
        Person firstFemale = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .findFirst()
                .orElse(null);

        System.out.println(firstFemale);

        // Multiple
        Optional<String> youngestMaleName = people.stream()
                .filter(person -> person.getGender().equals(Gender.MALE))
                .min(Comparator.comparing(Person::getAge))
                .map(Person::getName);

        youngestMaleName.ifPresent(System.out::println);

    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("Thomas", 35, Gender.MALE),
                new Person("Michael", 33, Gender.MALE),
                new Person("Ana", 23, Gender.FEMALE),
                new Person("William", 14, Gender.MALE),
                new Person("John", 17, Gender.MALE),
                new Person("Emma", 14, Gender.FEMALE),
                new Person("Sophia", 12, Gender.FEMALE),
                new Person("Mia", 18, Gender.FEMALE),
                new Person("Arthur", 44, Gender.MALE),
                new Person("Jack", 52, Gender.MALE),
                new Person("Elizabeth", 25, Gender.FEMALE),
                new Person("Emily", 31, Gender.FEMALE),
                new Person("Isabella", 11, Gender.FEMALE)
        );
    }

}
