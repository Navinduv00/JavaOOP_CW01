package Navindu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    Formula1ChampionshipManager newChampion = new Formula1ChampionshipManager();
    public void start(){

        Scanner inputs = new Scanner(System.in);
        System.out.println("");
        System.out.println("\t------------------Welcome to the Formula1ChampionshipManager----------------------- ");
        System.out.println("\n\t 1.Add Driver \n\t 2.Delete Driver \n\t 3.Change driver \n\t" +
                " 4.Display Statistics \n\t 5.Driver Table \n\t 6.Save File \n\t 7.Load Data \n\t 8.Add Race \n\t 9.Launch GUI" +
                " \n\t 99.Exit Program \n\t ");
        System.out.println("\t--------------------------------------------------------------------------------------");
        int number1 = 0;
        do {
            while (!inputs.hasNextInt()) {
                System.out.println("That's not a number!");
                inputs.next();
            }
            number1 = inputs.nextInt();
        } while (number1 <= 0);{
        switch (number1) {
            case 1:
                option1();
                break;
            case 2:
                option2();
                break;
            case 3:
                option3();
                break;
            case 4:
                newChampion.displayStats();
                start();
                break;
            case 5:
                option5();
                break;
            case 6:
                try {
                    newChampion.savedData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                start();
                break;
            case 7:
                try {
                    newChampion.loadDate();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                start();
                break;
            case 8:
                if (newChampion.driverArraySize() < 1) {
                    System.out.println("Not enough Racers start the race");
                } else {
                    newChampion.addRace();
                }
                start();
                break;
            case 9:
                new ChampionGUI();
            default:
                break;
           }
        }

    }
    public void option1(){ /*Option 1 is basically like add driver when this method is called it will
                                 ask the user to add name,location,manufacture. then it will check(validate)
                                  if theres a driver with the same details in already entered in the Formula1Championship class */
        Scanner inputs = new Scanner(System.in);
        System.out.println("+---------------------------+");
        System.out.println("Adding Driver..");
        System.out.println("+---------------------------+");
        System.out.println("Enter name");
        String name = inputs.nextLine().toLowerCase(Locale.ROOT).trim();
        System.out.println("Enter Location");
        String location = inputs.nextLine().toLowerCase(Locale.ROOT).trim();
        System.out.println("Enter Manufacturer");
        String manufacturer = inputs.nextLine().toLowerCase(Locale.ROOT).trim();
        boolean validate = isManufacturerExist(newChampion,manufacturer);
        while (true){//validation
              if (validate){
                  System.out.println("+---------------------------+");
                  System.out.println("Driver Already exists");
                  System.out.println("+---------------------------+");
                  System.out.println("Enter Manufacturer again");
                  manufacturer = inputs.nextLine().toLowerCase(Locale.ROOT).trim();
                  validate = isManufacturerExist(newChampion,manufacturer);
              }
              if (!validate){
                  newChampion.addDriver(name,location,manufacturer);//if theirs no entry like the one user entered
                                                                    // the details will be ended
                  System.out.println("+---------------------------+");
                  System.out.println("Driver added Successfully..");
                  System.out.println("+---------------------------+");
                  break;
              }
         }
        start();// after everything is added it will loop back to the menu
        }

    public void option2(){/*Option 2 is the method equals to Delete driver in Championship manager interface
                            method will ask the user the name and manufacture and see if it already exists then
                            if it is will delete or if it dosent exists will ask the user to try again*/
        Scanner inputs2 = new Scanner(System.in);
        System.out.println("Removing driver..");
        System.out.println("+-----------------+");
        newChampion.displayStats();
        System.out.println("Please select the Driver name you want to remove");
        String name = inputs2.nextLine().toLowerCase(Locale.ROOT).trim();
        System.out.println("Please enter Manufacturer");
        String manufacturer = inputs2.nextLine().toLowerCase(Locale.ROOT).trim();
        boolean result = newChampion.deleteDriver(name,manufacturer);/*Boolean is called and if its true that means
                                                                      the driver is deleted or else it means the driver
                                                                       havent been deleted*/
        if (result){
            System.out.println("+---------------------------+");
            System.out.println("Player deleted successfully");
            System.out.println("+---------------------------+");

        }else{
            System.out.println("Try again");
            option2();
        }
        start();

    }
    public void option3(){/*Option 3 is like Change driver where the user gets the chance to change a exisitng driver from
                            one team to another
                            This only works if theirs more than 1 Manufacture(Teams)Created*/

        Scanner input3 = new Scanner(System.in);
        newChampion.displayStats();
        System.out.println("Enter Existing Manufacturer ");
        String newManufacturer =  input3.nextLine();
        if (!isManufacturerExist(newChampion,newManufacturer)){
            System.out.println("Manufacturer requested is not existing");
            option3();
        }
        else {
            System.out.println("Enter name");
            String name = input3.nextLine();
            System.out.println("Enter Current Manufacturer");
            String manufacturer = input3.nextLine();
            boolean validate = newChampion.changeDriver(name,manufacturer,newManufacturer);
            while (true){
                if (validate){
                    System.out.println("Driver Already exists");
                    System.out.println("Enter Driver again");
                    name = input3.nextLine();
                    System.out.println("Enter Manufacturer ");
                    manufacturer =  input3.nextLine();
                    validate = isManufacturerExist(newChampion,manufacturer);
                }else{
                    System.out.println("Driver Successfully Changed");
                    break;
                }
            }
        }
        start();
    }

    public void option5() {
        ArrayList<Formula1Driver> driverArray = newChampion.getDriverArray();
        Scanner input5 = new Scanner(System.in);
        System.out.println("Enter Driver name");
        String name = input5.nextLine().toLowerCase(Locale.ROOT).trim();
        System.out.println("Enter Manufacturer");
        String manufacturer = input5.nextLine().toLowerCase(Locale.ROOT).trim();
        boolean validate = true;
        for (int x=0;x<newChampion.driverArraySize();x++) {
            if (manufacturer.equalsIgnoreCase(driverArray.get(x).getManufacturer())) {
                newChampion.displayDriverStat(name,manufacturer);
                validate = false;
                break;
            }
        }if (validate){
            System.out.println("+----------------------+");
            System.out.println("Driver does not exist");
            System.out.println("+----------------------+");
        }
        start();
    }
    public static boolean isManufacturerExist(Formula1ChampionshipManager formula1ChampionshipManager,String manufacturer) {
        for (Formula1Driver driver : formula1ChampionshipManager.getDrivers()) {
            if (driver.getManufacturer().equalsIgnoreCase(manufacturer)) {
                return true;
            }
        }
        return false;
    }

}


