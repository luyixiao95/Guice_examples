package no_guice;

public class NoGuiceExample {
    public static void main(String[] args) {
        Owner owner = new Owner(new BMW());
    }

}
final class Owner{
    private final Car car;

    Owner(Car car) {
        this.car = car;
    }
    public Car getCar() {
        return car;
    }
}

