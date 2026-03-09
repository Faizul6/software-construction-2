class Professor extends Person {
    public Professor(String fn, String ln) { super(fn, ln); }
    @Override public void displayInfo() { System.out.println("Professor: " + getFirstName() + " " + getLastName()); }
}
class Staff extends Person {
    public Staff(String fn, String ln) { super(fn, ln); }
    @Override public void displayInfo() { System.out.println("Staff: " + getFirstName() + " " + getLastName()); }
}
