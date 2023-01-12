package provider;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;




interface Car{
    int drive();
}

final class BMW implements Car {
    @Inject
    BMW() {

    }
    @Override
    public int drive() {
        return 60;
    }
    public static BMW create(){
        return new BMW();
    }
}

final class Owner{
    private final Car car;
    @Inject
    public Owner(Provider<Car> provider) {
        this.car = provider.get();
    }
    public Car getCar() {
        return car;
    }
    public int getSpeed() {
        return car.drive();
    }
}

final class CarModule extends AbstractModule {
    @Override
    protected void configure() {
        //Method 1: directly bind two classes
//        bind(Car.class).to(BMW.class) ;

        //Method 2: bind class to instance
        //bind(Car.class).toInstance(BMW.create());
    }
    @Provides
        @Singleton
        Car provideCar(){
            return BMW.create();
        }


}

public class ProviderExample {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new CarModule());
        Owner owner = injector.getInstance(Owner.class);
        System.out.println("Spped: " + owner.getCar().drive());

    }
}