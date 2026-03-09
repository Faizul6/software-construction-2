public interface Flyable {
    void fly();


    default int getMaxAltitude() {
        return 1000;
    }
}
