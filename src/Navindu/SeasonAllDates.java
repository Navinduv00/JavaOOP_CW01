package Navindu;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class SeasonAllDates implements Serializable {//this class was created to store all the data gathered in the season

    private String firstPlace;
    private String secondPlace;
    private String thirdPlace;
    private String fourthPlaces;
    private String fifthPlaces;
    private String sixthPlaces;
    private String seventhPlaces;
    private String eighthPlaces;
    private String ninthPlaces;
    private String tenthPlace;
    private ArrayList <ArrayList <Object>> aboveTenth= new ArrayList<>() ;
    private int day;
    private int month;
    private int year;

    int allTabRows = 0;

    public SeasonAllDates(int day, int month, int year, ArrayList<Integer> takenPlaces, ArrayList<String> names){
        this.day = day;
        this.month = month;
        this.year = year;
        for (int i=0; i < takenPlaces.size(); i++){
            if (takenPlaces.get(i) == 1) {
                firstPlace = names.get(i);
            } else if (takenPlaces.get(i) == 2) {
                secondPlace = names.get(i);

            } else if (takenPlaces.get(i) == 3) {
                thirdPlace = names.get(i);
            } else if (takenPlaces.get(i) == 4) {
                fourthPlaces = names.get(i);
            }else if (takenPlaces.get(i) == 5) {
                fifthPlaces = names.get(i);
            } else if (takenPlaces.get(i) == 6) {
                sixthPlaces = names.get(i);
            } else if (takenPlaces.get(i) == 7){
                seventhPlaces = names.get(i);
            }else if (takenPlaces.get(i) == 8) {
                eighthPlaces = names.get(i);
            } else if (takenPlaces.get(i) == 9) {
                ninthPlaces = names.get(i);
            } else if (takenPlaces.get(i) == 10){
                tenthPlace = names.get(i);
            }else{
                ArrayList<Object> tempArray = new ArrayList<>();
                tempArray.add(takenPlaces.get(i));
                tempArray.add(names.get(i));
                aboveTenth.add(tempArray);
            }
        }

    }
    public SeasonAllDates(int[] dateArray) {
        this.day = dateArray[0];
        this.month = dateArray[1];
        this.year = dateArray[2];
    }
    public void getPosName(String name, int position){
        if (position > 0) {
            if (position == 1) {
                firstPlace = name;
            } else if (position == 2) {
                secondPlace = name;

            } else if (position == 3) {
                thirdPlace = name;
            } else if (position == 4) {
                fourthPlaces = name;
            }else if (position == 5) {
                fifthPlaces = name;
            } else if (position == 6) {
                sixthPlaces = name;
            } else if (position == 7){
                seventhPlaces = name;
            }else if (position == 8) {
                eighthPlaces = name;
            } else if (position == 9) {
                ninthPlaces = name;
            } else if (position == 10){
                tenthPlace = name;
            }else{
                ArrayList<Object> tempArray = new ArrayList<>();
                tempArray.add(position);
                tempArray.add(name);
                aboveTenth.add(tempArray);
            }
        }
    }
    public String dateGen(){
        String date = day + "-" +month +"-"+ year ;
        return date;
    }

    public String getFirstPlace() {
        return firstPlace;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public String getThirdPlace() {
        return thirdPlace;
    }

    public String getFourthPlaces() {
        return fourthPlaces;
    }

    public String getFifthPlaces() {
        return fifthPlaces;
    }

    public String getSixthPlaces() {
        return sixthPlaces;
    }

    public String getSeventhPlaces() {
        return seventhPlaces;
    }

    public String getEighthPlaces() {
        return eighthPlaces;
    }

    public String getNinthPlaces() {
        return ninthPlaces;
    }

    public String getTenthPlace() {
        return tenthPlace;
    }
}