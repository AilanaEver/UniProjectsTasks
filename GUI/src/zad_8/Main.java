package zad_8;
import java.awt.Color;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    public class Main {

        public static void main(String[] args) {

            Scanner scan = null;
            try{
                scan = new Scanner(Paths.get("C:\\GUI\\src\\zad_8"));
            }catch(IOException e){
                e.printStackTrace();
            }

            List<Person> personList = new ArrayList<>();

            assert scan != null : "File IS empty!";
            while(scan.hasNextLine()){

                String line = scan.nextLine();
                String[] sArr = line.split("\\s+");

                if(sArr.length == 2){

                    String name = sArr[0];
                    int year = Integer.parseInt(sArr[1]);
                    Car car = null;
                    personList.add(new Person(name, year, car));

                }else if(sArr.length == 6){

                    String name = sArr[0];
                    int year = Integer.parseInt(sArr[1]);
                    Car car = new Car(sArr[2], new Color(
                            Integer.parseInt(sArr[3]),
                            Integer.parseInt(sArr[4]),
                            Integer.parseInt(sArr[5])
                    ));
                    personList.add(new Person(name, year, car));

                }else System.out.println("Wrong input!!!");
            }

            System.out.println(Person.findAllCars(personList));

            System.out.println(Person.findOwners(personList, "mercedes"));

            Color color = Person.findColor(personList, "Alice", 1993);
            if(color != null)
                System.out.println(color.getRed() + " " + color.getGreen() + " " + color.getBlue());
            else System.out.println("Such car color has not been found!");

        }
    }
class Person {
    String name;
    int yearOfBirth;
    Car car;

    Person(String n, int y, Car c){
        name = n;
        yearOfBirth = y;
        car = c;
    }

    static List<Car> findAllCars (List<Person> list){
        ArrayList<Car> cars = new ArrayList<>();

        for (int i=0; i<list.size(); i++){
            cars.add(list.get(i).car);
        }

        return cars;
    }

    static List<Person> findOwners(List<Person> list, String carName){
        ArrayList<Person> owners = new ArrayList<>();

        for (int i=0; i<list.size(); i++){
            if (list.get(i).car.name == carName)
                owners.add(list.get(i));
        }

        return owners;
    }

    public static Color findColor(List<Person> list, String name, int year){

        for (Person person : list) {
            if(person.getName().equalsIgnoreCase(name)
                    && person.getYearOfBirth() == year
                    && person.getCar() != null)
                return person.getCar().getColor();
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public Car getCar(){
        return car;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public String toString(){
        return name + " " + yearOfBirth + " " + car;
    }

}

class Car {

        String name;
        Color color;

        Car(String n, Color c){
            name = n;
            color = c;
        }


    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return name + " " + color.getRed() + " " + color.getGreen() + " " + color.getBlue();
    }
}