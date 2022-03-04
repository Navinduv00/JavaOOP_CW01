package Navindu;


import java.io.Serializable;
import java.util.ArrayList;

public class Formula1Driver extends Driver implements Serializable,Comparable<Formula1Driver> {//this class was created
                                                                // to store data of one driver constructor at a time

    ArrayList <ArrayList<Integer>> dateDetails = new ArrayList<>();

    private int firstPlace;
    private int secondPlace;
    private int thirdPlace;
    private int totalPoints;
    private int totalraces;
    private int fourthPlaces;
    private int fifthPlaces;
    private int sixthPlaces;
    private int seventhPlaces;
    private int eighthPlaces;
    private int ninthPlaces;
    private int tenthPlace;
    private int aboveTenth;

    public Formula1Driver() {

    }

    public Formula1Driver(String name, String manufacturer, String location) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.location = location;

    }

    public Formula1Driver(int firstPlace, int secondPlace, int thirdPlace,int forthplace
            , int fifthplace , int sixthPlace, int seventhPlace , int eighthPlace, int ninethPlace,
                          int tenthPlace ,int aboveTenth , int totalPoints, int totalraces) {
        this.firstPlace = firstPlace;
        this.secondPlace = secondPlace;
        this.thirdPlace = thirdPlace;
        this.fourthPlaces = forthplace;
        this.fifthPlaces = fifthplace;
        this.sixthPlaces = sixthPlace;
        this.seventhPlaces = seventhPlace;
        this.eighthPlaces = eighthPlace;
        this.ninthPlaces = ninethPlace;
        this.tenthPlace = tenthPlace;
        this.aboveTenth = aboveTenth;
        this.totalPoints = totalPoints;
        this.totalraces = totalraces;
    }
    public void incrementinOtherPlaces(int achievedPlace) {
        if (achievedPlace > 0) {
            if (achievedPlace == 1) {
                this.firstPlace++;
            } else if (achievedPlace == 2) {
                this.secondPlace++;
            } else if (achievedPlace == 3) {
                this.thirdPlace++;
            } else if (achievedPlace == 4) {
                this.fourthPlaces++;
            }else if (achievedPlace == 5) {
                this.fifthPlaces++;
            } else if (achievedPlace == 6) {
                this.sixthPlaces++;
            } else if (achievedPlace == 7){
                this.seventhPlaces++;
            }else if (achievedPlace == 8) {
                this.eighthPlaces++;
            } else if (achievedPlace == 9) {
                this.ninthPlaces++;
            } else if (achievedPlace == 10){
                this.tenthPlace++;
            }else{
                this.aboveTenth++;
            }
        } else throw new IllegalArgumentException("Place should be a positive Integer!");
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;

    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public int getFirstPlace() {
        return firstPlace;
    }

    @Override
    public int getSecondPlace() {
        return secondPlace;
    }


    @Override
    public int getThirdPlace() {
        return thirdPlace;
    }

    @Override
    public int getTotalraces() {
        return totalraces;
    }

    @Override
    public int getTotalPoints() {
        setTotalPoints(driverPointsCalculator());
        return totalPoints;
    }
    @Override
    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Override
    public int driverPointsCalculator(){
        int total1 = this.firstPlace*25 + this.secondPlace*18 + this.thirdPlace*15;
        int total2 = fourToTentPositionCalculator();
        return total1 + total2;
    }
    @Override
    public int compareTo(Formula1Driver o) {
        int result = this.getTotalPoints() - o.getTotalPoints();
        if (result == 0){
            return this.getFirstPlace() - o.getFirstPlace();
        }
        return result;
    }

    public Formula1Driver(int fourthPlaces, int fifthPlaces, int sixthPlaces, int seventhPlaces, int eighthPlaces,
                          int ninthPlaces, int tenthPlace, int aboveTenth) {
        this.fourthPlaces = fourthPlaces;
        this.fifthPlaces = fifthPlaces;
        this.sixthPlaces = sixthPlaces;
        this.seventhPlaces = seventhPlaces;
        this.eighthPlaces = eighthPlaces;
        this.ninthPlaces = ninthPlaces;
        this.tenthPlace = tenthPlace;
        this.aboveTenth = aboveTenth;
    }

    public int getFourthPlaces() {
        return fourthPlaces;
    }


    public int getFifthPlaces() {
        return fifthPlaces;
    }

    public int getSixthPlaces() {
        return sixthPlaces;
    }

    public int getSeventhPlaces() {
        return seventhPlaces;
    }

    public int getEighthPlaces() {
        return eighthPlaces;
    }

    public int getNinthPlaces() {
        return ninthPlaces;
    }

    public int getTenthPlace() {
        return tenthPlace;
    }

    public int getAboveTenth() {
        return aboveTenth;
    }

    public int fourToTentPositionCalculator(){
        return getFourthPlaces()*12 + getFifthPlaces()*10 + getSixthPlaces()*8 + getSeventhPlaces()*6 +
                getEighthPlaces()*4 + getNinthPlaces()*2 + getTenthPlace();
    }

    public void getDate(int [] array,int position){
        ArrayList<Integer>posDate = new ArrayList<>();
        posDate.add(array[0]);
        posDate.add(array[1]);
        posDate.add(array[2]);
        posDate.add(position);
        dateDetails.add(posDate);
    }
    public ArrayList <ArrayList<Integer>> getDateDetials(){
        return dateDetails;
    }
    public int getDate(int x){
        return dateDetails.get(x).get(0);
    }
    public int getMonth(int x){
        return dateDetails.get(x).get(1);
    }
    public int getYear(int x){
        return dateDetails.get(x).get(2);
    }
    public int getPosition(int x){
        return dateDetails.get(x).get(3);
    }
    public int getDateDetailArraySize(){
        return dateDetails.size();
    }
}



