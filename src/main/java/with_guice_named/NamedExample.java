package with_guice_named;


import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import javax.inject.Inject;
import javax.inject.Qualifier;
import javax.inject.Singleton;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

interface Car {
    int drive();
}

final class BMW implements Car {
    @Inject BMW() {}

    @Override
    public int drive() {
        return 60;
    }

    public static Car create() {
        return new BMW();
    }
}

final class Honda implements Car {
    @Inject
    Honda() {}

    @Override
    public int drive() {
        return 50;
    }
    public static Car create() {
        return new Honda();
    }
}

final class Owner{
    private final Car car;
    @Inject
    public Owner(@CarHonda Car car) {
        this.car = car;
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
//        bind(Car.class).annotatedWith(CarBMW.class).to(BMW.class);
//        bind(Car.class).annotatedWith(CarHonda.class).to(Honda.class);

        //Method 2: bind class to instance
        //bind(Car.class).toInstance(BMW.create());

    }
    @Provides
    @CarBMW
    Car provideBMWCar(){
        return BMW.create();
    }
    @Provides
    @CarHonda
    Car provideHonda (){
        return Honda.create();
    }
}

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, PARAMETER, METHOD})
@interface
CarBMW{}

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, PARAMETER, METHOD})
@interface
CarHonda{}

public class NamedExample {
}
