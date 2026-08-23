public class Duck extends Animal implements Flyable, Swimmable {
    public Duck(String name, int age) {
        super(name, "Duck", age);
    }
    @Override
    public void makeSound() {
        System.out.println(name + " says Quack!");
    }
    @Override
    public void fly() {
        System.out.println(name + " flies  over the pond!");
    }


    @Override
    public void swim() {
        System.out.println(name + " swims in the water!");
    }
}
