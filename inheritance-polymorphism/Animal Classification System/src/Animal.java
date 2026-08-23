public abstract class Animal {
    protected String name;
    protected String species;
    protected int age;


    public Animal (String name, String species, int age) {
        this.name = name;
        this.species = species;
        this.age = age;
    }


    public void displayInfo() {
        System.out.printf("%s is a %d year old %s (%s).%n", name, age, species, this.getClass().getSimpleName());
    }


    public abstract void makeSound();
}


