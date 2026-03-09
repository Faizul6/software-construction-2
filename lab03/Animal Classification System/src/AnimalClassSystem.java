import java.util.ArrayList;
import java.util.List;


public class AnimalClassSystem {
    public static void demonstrate() {
        System.out.println("\n--- Task 3: Animal Classification System ---");


        List<Animal> animals = new ArrayList<>();
        animals.add(new Duck("Lucas", 3));
        animals.add(new Bird("Piolin", 1));
        animals.add(new Fish("Nemo", 2));
        animals.add(new Dog("Fido", 5));


        // Demonstrate Animal polymorphism
        for (Animal a : animals) {
            a.displayInfo();
            a.makeSound();


            if (a instanceof Flyable) {
                ((Flyable) a).fly();
            }
            if (a instanceof Swimmable) {
                ((Swimmable) a).swim();
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        demonstrate();
    }
}
