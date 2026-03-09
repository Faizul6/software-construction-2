public class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, "Dog", age);
    }


    @Override
    public void makeSound() {
        System.out.println(name + " says Woof,!");
        System.out.println(name + " is a good boy!");
    }
}
