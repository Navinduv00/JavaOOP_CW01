package Navindu;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Driver implements Serializable { //OOP concept Abstraction is followed here where the user will only see
                                // essential processes of the program
    String name;
    String manufacturer;
    String location;

    public abstract String getName() ;

    public abstract String getManufacturer();

    public abstract void setManufacturer(String manufacturer);

    public abstract String getLocation();

    public abstract int getFirstPlace() ;

    public abstract int getSecondPlace() ;

    public abstract int getThirdPlace();

    public abstract int getTotalraces();

    public abstract void setTotalPoints(int totalPoints);

    public abstract int getTotalPoints();

    public abstract int driverPointsCalculator();
}
