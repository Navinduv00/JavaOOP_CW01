package Navindu;

import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Formula1ChampionshipManager implements ChampionshipManager {//implements the ChampionshipManager Interface
    private ArrayList<Formula1Driver> drivers = new ArrayList<>();
    public ArrayList<Formula1Driver> getDrivers() {
        return drivers;
    }
    public ArrayList<SeasonAllDates> getDate = new ArrayList<>();

    int dateIncrement = 0;

    @Override
    public void addDriver(String name, String location, String manufacturer) {
        drivers.add(new Formula1Driver(name,manufacturer,location));
        drivers.sort(Collections.reverseOrder());
    }

    //every method is run through a for loop to get all the drivers inside the drivers ArrayList (starts from 0 index)

    @Override
    public boolean deleteDriver(String name, String manufacturer) {
        for(int i=0; i<drivers.size();i++){
            if(drivers.get(i).getName().equals(name) && drivers.get(i).getManufacturer().equals(manufacturer)){
                drivers.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean changeDriver(String name, String currentManufacturer, String newManufacturer) {
        boolean validate = false;
        for(int i=0; i<drivers.size();i++){
            if(drivers.get(i).getName().equals(name) && drivers.get(i).getManufacturer().equals(currentManufacturer)){
                drivers.get(i).setManufacturer(newManufacturer);
                validate =true;
            }
        }
        return validate;
    }


    @Override
    public void displayDriverStat(String name, String manufacturer) {
        String leftAlignFormat = "| %-15s | %-10s | %-8d | %-8d | %-8d | %-8d | %-8d | %-8d | %-8d | %-8d | %-8d | %-8d | %-8d | %-8d | %-8d | %n";
        System.out.println("+---------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|Name             |Team        |Total     |Total     |First     |Second    |Third    |Fourth     |Fifth    |Sixth    |Seventh     |Eighth    |Ninth   |Tenth   |Tenth");
        System.out.println("                               | Points   |  Races   |  Places  |  Places  |  Places |  Places   | Places  |  Places |  Places    | Places   |Places  | Places | Tenth ");
        System.out.println("+---------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
        for(int i=0; i<drivers.size();i++) {
            if (drivers.get(i).getName().equals(name) && drivers.get(i).getManufacturer().equals(manufacturer)) {
                System.out.format(leftAlignFormat, drivers.get(i).getName(), drivers.get(i).getManufacturer()
                        , drivers.get(i).getTotalPoints(), drivers.get(i).getTotalraces(), drivers.get(i).getFirstPlace(),
                        drivers.get(i).getSecondPlace(), drivers.get(i).getThirdPlace(), drivers.get(i).getFourthPlaces(),
                        drivers.get(i).getFifthPlaces(), drivers.get(i).getSixthPlaces(), drivers.get(i).getSeventhPlaces(),
                        drivers.get(i).getEighthPlaces(), drivers.get(i).getNinthPlaces(), drivers.get(i).getTenthPlace(),
                        drivers.get(i).getAboveTenth());
            }
        }
        System.out.println("+---------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
    }

    @Override
    public void displayStats() {
        String leftAlignFormat = "| %-10d | %-15s | %-15s  |%n";
        System.out.println("+-------------------------------------------------+");
        System.out.println("|Number      |Name             |Team              |");
        System.out.println("+-------------------------------------------------+");
        for (int x = 0; x < drivers.size(); x++) {
            System.out.format(leftAlignFormat, x, drivers.get(x).getName(), drivers.get(x).getManufacturer());
        }
        System.out.println("+-------------------------------------------------+");
    }

    @Override
    public void addRace() {
        int[] Array = new int[3];
        boolean race = true;
        Scanner newRace = new Scanner(System.in);
        while (race) {
            System.out.println("Enter Date");
            int date = newRace.nextInt();
            if (date > 31 || date < 1) {
                System.out.println("Not a valid date");
            } else {
                Array[0]= date;
                race = false;
            }
        }
        race = true;
        while (race) {
            System.out.println("Enter Month");
            int month = newRace.nextInt();
            if (month > 12 || month < 1) {
                System.out.println("Not a valid month");
            } else {
                Array[1]= month;
                race = false;
            }
        }
        race= true;
        while (race) {
            System.out.println("Enter Year");
            int year = newRace.nextInt();
            if (String.valueOf(year).length()!= 4) {
                System.out.println("Not a valid year");
            } else {
                Array[2]= year;
                race = false;
            }
        }
        getDate.add(new SeasonAllDates(Array));
        for (int x = 0; x < drivers.size(); x++) {
            System.out.println(drivers.get(x).getName() + " - ");
            int position = newRace.nextInt();
            while (position > drivers.size() || position < 1) {
                System.out.println("Enter a valid position - ");
                position = newRace.nextInt();
            }
            drivers.get(x).incrementinOtherPlaces(position);
            drivers.get(x).getDate(Array,position);
            getDate.get(dateIncrement).getPosName(drivers.get(x).name,position);
        }
        dateIncrement++;
        for (int x = 0; x < getDate.size(); x++){
            getDate.get(x).dateGen();
        }

    }

    @Override
    public void savedData() throws IOException {
        File newfile = new File("NavinduCW.txt");

        FileOutputStream outputfile = new FileOutputStream(newfile);
        ObjectOutputStream outputObject = new ObjectOutputStream(outputfile);

        outputObject.writeObject(drivers);
        outputObject.writeObject(getDate);

        outputObject.close();
        outputfile.close();
        System.out.println("+-------Saved data----------+");
        for (int x=0; x< drivers.size();x++){
            System.out.println(drivers.get(x).getName());
        }
        System.out.println("+-------Data Found----------+");
    }
    @Override
    @SuppressWarnings("unchecked")
    public void loadDate() throws IOException {
        FileInputStream inputfile= new FileInputStream("NavinduCW.txt");
        ObjectInputStream inputobject= new ObjectInputStream(inputfile);
        while (true) {
            try {
                drivers = (ArrayList<Formula1Driver>) inputobject.readObject();
                getDate = (ArrayList<SeasonAllDates>) inputobject.readObject();
            } catch (IOException|ClassNotFoundException e) {
                break;
            }
        }
        for (int x=0; x< drivers.size();x++){
            System.out.println("Driver "+ x);
            System.out.println(drivers.get(x).getName());
        }
    }


    public int driverArraySize(){
        return drivers.size();}

    public void setDrivers(ArrayList<Formula1Driver> drivers) {
        this.drivers = drivers;
    }

    public ArrayList<Formula1Driver> getDriverArray(){
        return drivers;
    }

}