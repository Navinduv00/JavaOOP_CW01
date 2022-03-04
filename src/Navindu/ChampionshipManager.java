package Navindu;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ChampionshipManager {
      void addDriver(String name,String location, String manufacturer);
      boolean deleteDriver(String name, String manufacturer);
      boolean changeDriver(String name,String currentManufacturer, String newManufacturer);
      void displayDriverStat(String name, String manufacturer);
      void displayStats();
      void addRace();
      void savedData() throws IOException;
      void loadDate() throws IOException;

}
