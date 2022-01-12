package Lesson_5;

import java.util.Arrays;
import java.util.Comparator;

enum Sex{M,F}
enum Size{XS,S,M,L,XL}
enum Country {
    PL {
        @Override
        public String toString() { return " Polska";}
    },
    NL {
        @Override
        public String toString() { return " Nederland"; }
    },
    DE {
        @Override
        public String toString() { return " Deutschland"; }
    }
}
    class Person {
    private String name;
    private Sex sex;
    private Size size;
    private Country country;

    Person(String name, Sex sex, Size size, Country country) {
        this.name = name;
        this.sex = sex;
        this.size = size;
        this.country = country;
    }

    public String getName() { return name; }
    public Sex getSex() { return sex; }
    public Size getSize() { return size; }


    public String toString() {
        return name + "(" + sex + ", " + size + "," + country +  ")" +"\n";
    }
}

public class EnumsLambdas {

    static <T> void printArray(String message, T[] arr) {
        System.out.println(message);
        for (T t : arr)
            System.out.print(t.toString());
        System.out.println("\n ");
    }


    public static void main(String[] args){
        Person[] persons = {
                new Person ("Max", Sex.M, Size.XL, Country.NL),
                new Person ("Jan", Sex.M, Size.S, Country.PL),
                new Person ("Eva", Sex.F, Size.XS, Country.NL),
                new Person ("Lina", Sex.F, Size.L, Country.DE),
                new Person ("Mila", Sex.F, Size.S, Country.DE),
                new Person ("Ola", Sex.F, Size.M, Country.PL),
        };

        Comparator<Person> sexThenSize = (p1, p2) -> {
            if(p1.getSex().ordinal()-p2.getSex().ordinal()!=0){
                return p1.getSex().ordinal()-p2.getSex().ordinal();
            } else
                return p1.getSex().ordinal()-p2.getSex().ordinal();
        };
        Arrays.sort(persons, sexThenSize);
        printArray("Persons by sex and then size", persons);
        Arrays.sort(persons, (p1,p2) -> {
            if (p1.getSize().ordinal() - p2.getSize().ordinal() != 0) {
                return p1.getSize().ordinal() - p2.getSize().ordinal();
            } else
                return p1.getName().compareToIgnoreCase(p2.getName());
        });

        printArray("Persons by size and then name", persons);
        Country[] countries = Country.values();
        Arrays.sort(countries,(c1, c2) -> c2.compareTo(c1));
        printArray("Countries by name", countries);
    }

}
