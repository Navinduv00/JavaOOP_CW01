package Navindu;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


class ChampionGUI {

    JFrame jFrame;

    ArrayList<Formula1Driver> drivers = new ArrayList<>();
    ArrayList<SeasonAllDates> getDate = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public void loadDate() throws IOException {//Loading data when the gui is launched
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
        System.out.println("+-------Data Found----------+");
        for (int x=0; x< drivers.size();x++){
            System.out.println(drivers.get(x).getName());
        }
        System.out.println("+---------------------------+");
    }


    public ChampionGUI(){//initializing the GUI
        try {
            loadDate();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Champion GUI");
        frame.setBackground(new Color(127,255,191));

        //implementing images for buttons
        ImageIcon imageadd = new ImageIcon("add.png");
        ImageIcon imageDelete = new ImageIcon("delete.png");
        ImageIcon imageRacer = new ImageIcon("racer.png");
        ImageIcon imageTable = new ImageIcon("table.png");
        ImageIcon imageSearch = new ImageIcon("search.png");
        ImageIcon imageDate = new ImageIcon("Date.png");
        ImageIcon imageRandom = new ImageIcon("random.png");

        //button functionality separately
        JButton btnAdd = new JButton();
        btnAdd.setBounds(160,150,250,100);
        btnAdd.setText("View Table");
        btnAdd.setFocusable(false);
        btnAdd.setIcon(imageadd);
        btnAdd.setBackground(Color.white);
        btnAdd.setForeground(Color.GRAY);
        btnAdd.setBorder(BorderFactory.createEtchedBorder());
        btnAdd.setOpaque(true);
        btnAdd.setBorderPainted(true);

        JButton btnSortAccending = new JButton();
        btnSortAccending.setBounds(160,250,250,100);
        btnSortAccending.addActionListener(e -> new ChampionGUI());
        btnSortAccending.setText("Racers in Ascending Order ");
        btnSortAccending.setFocusable(false);
        btnSortAccending.setIcon(imageTable);
        btnSortAccending.setBackground(Color.white);
        btnSortAccending.setForeground(Color.GRAY);
        btnSortAccending.setBorder(BorderFactory.createEtchedBorder());
        btnSortAccending.setOpaque(true);
        btnSortAccending.setBorderPainted(true);

        JButton btnRadomRace = new JButton();
        btnRadomRace.setBounds(160,350,250,100);
        btnRadomRace.setText("Random Race");
        btnRadomRace.setFocusable(false);
        btnRadomRace.setIcon(imageRandom);
        btnRadomRace.setBackground(Color.white);
        btnRadomRace.setForeground(Color.GRAY);
        btnRadomRace.setBorder(BorderFactory.createEtchedBorder());
        btnRadomRace.setOpaque(true);
        btnRadomRace.setBorderPainted(true);

        JButton btnSortFirstPos = new JButton();
        btnSortFirstPos.setBounds(160,450,250,100);
        btnSortFirstPos.setText("Sort by First Place");
        btnSortFirstPos.setFocusable(false);
        btnSortFirstPos.setIcon(imageRacer);
        btnSortFirstPos.setBackground(Color.white);
        btnSortFirstPos.setForeground(Color.GRAY);
        btnSortFirstPos.setBorder(BorderFactory.createEtchedBorder());
        btnSortFirstPos.setOpaque(true);
        btnSortFirstPos.setBorderPainted(true);

        JButton btnCompletedRaces = new JButton();
        btnCompletedRaces.setBounds(160,550,250,100);
        btnCompletedRaces.setText("All Completed Races");
        btnCompletedRaces.setFocusable(false);
        btnCompletedRaces.setIcon(imageDate);
        btnCompletedRaces.setBackground(Color.white);
        btnCompletedRaces.setForeground(Color.GRAY);
        btnCompletedRaces.setBorder(BorderFactory.createEtchedBorder());
        btnCompletedRaces.setOpaque(true);
        btnCompletedRaces.setBorderPainted(true);

        JButton btnSearchDriver = new JButton();
        btnSearchDriver.setBounds(200,650,250,100);
        btnSearchDriver.setText("Search Driver");
        btnSearchDriver.setFocusable(false);
        btnSearchDriver.setIcon(imageSearch);
        btnSearchDriver.setBackground(Color.white);
        btnSearchDriver.setForeground(Color.GRAY);
        btnSearchDriver.setBorder(BorderFactory.createEtchedBorder());
        btnSearchDriver.setOpaque(true);
        btnSearchDriver.setBorderPainted(true);

        //Text Fields
        JTextField searchD = new JTextField();
        searchD.setBounds(80,650,250,100);

        //Labels
        JLabel welcomeLabel = new JLabel();
        welcomeLabel.setText("Welcome to Champion GUI");
        welcomeLabel.setBounds(200,80,250,100);

        //implementation
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600,800);
        frame.setLayout(null);
        frame.add(welcomeLabel);
        frame.add(btnAdd);
        frame.add(btnSortAccending);
        frame.add(btnSortFirstPos);
        frame.add(btnCompletedRaces);
        frame.add(btnRadomRace);
        frame.add(btnSearchDriver);
        frame.add(searchD);

        btnAdd.addActionListener(new ActionListener() {//showing driver table in descending order
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame = new JFrame("Table");
                jFrame.setSize(1500,400);
                jFrame.setLayout(null);
                jFrame.getContentPane().setBackground(new Color(35,31,32));
                String[] column = {"Name", "Manufacture","Location","1st Places","2nd Places","3rd Places","4th Places"
                        ,"5th Places","6th Places","7th Places","8th Places","9th Places","10th Places","Other Places"
                        ,"Total Points","Total Races"};
                Object[][] rows = new Object[drivers.size()][16]; //driver.size() is to count thr rows of the table and
                sortDescending();
                setRows(rows);
                JTable jTable = new JTable(rows,column);
                JScrollPane sp=new JScrollPane(jTable);
                sp.setBounds(120,100,1200,700);

                jFrame.add(sp);
                jFrame.setVisible(true);
            }
        });
        btnSortAccending.addActionListener(new ActionListener() {//sorting table in acceding order button
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame = new JFrame("Table in Ascending Order");
                jFrame.setSize(1200,600);
                jFrame.setLayout(null);
                jFrame.getContentPane().setBackground(new Color(35,31,32));

                // Adding label
                JLabel dLabel = new JLabel();
                dLabel.setText("Sorting Races in Ascending Order");
                dLabel.setBounds(500,50,600,50);
                dLabel.setForeground(Color.RED);

                Object [][] rows = new Object[drivers.size()][16];
                sortAscending();
                setRows(rows);
                String[] column = {"Name", "Manufacture","Location","1st Places","2nd Places","3rd Places","4th Places"
                        ,"5th Places","6th Places","7th Places","8th Places","9th Places","10th Places","Other Places"
                        ,"Total Points","Total Races"};
                JTable table = new JTable(rows,column);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(50,180,1100,183);

                jFrame.add(scrollPane);
                jFrame.add(dLabel);
                jFrame.setVisible(true);


            }
        });

        btnSortFirstPos.addActionListener(new ActionListener() {//sorting table according to the first position button
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame = new JFrame("First Pos Ascending");
                jFrame.setSize(1200,600);
                jFrame.setLayout(null);
                jFrame.getContentPane().setBackground(new Color(35,31,32));

                // Adding label
                JLabel dLabel = new JLabel();
                dLabel.setText("Tables Sorted Through First Positions");
                dLabel.setBounds(500,50,600,50);
                dLabel.setForeground(Color.RED);

                Object [][] rows = new Object[drivers.size()][16];
                sortingFirstPositions();
                setRows(rows);
                String[] column = {"Name", "Manufacture","Location","1st Places","2nd Places","3rd Places","4th Places"
                        ,"5th Places","6th Places","7th Places","8th Places","9th Places","10th Places","Other Places"
                        ,"Total Points","Total Races"};
                JTable table = new JTable(rows,column);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(50,180,1100,183);

                jFrame.add(scrollPane);
                jFrame.add(dLabel);
                jFrame.setVisible(true);
            }
        });

        btnSearchDriver.addActionListener(new ActionListener() {//search driver button
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = searchD.getText();
                for (int x = 0;drivers.size() > x;x++){
                    if (drivers.get(x).getName().equalsIgnoreCase(search)){
                        jFrame = new JFrame("Driver Search");
                        jFrame.setSize(1200,600);
                        jFrame.setLayout(null);
                        jFrame.getContentPane().setBackground(new Color(35,31,32));

                        // Adding label
                        JLabel dLabel = new JLabel();
                        dLabel.setText("Search Driver");
                        dLabel.setBounds(500,50,600,50);
                        dLabel.setForeground(Color.RED);

                        Object [][] rows = new Object[drivers.size()][4];
                        newSetRows(rows,x);
                        String[] column = {"Date","Month","Year","Position"};
                        JTable table = new JTable(rows,column);
                        JScrollPane scrollPane = new JScrollPane(table);
                        scrollPane.setBounds(50,180,1100,183);

                        jFrame.add(scrollPane);
                        jFrame.add(dLabel);
                        jFrame.setVisible(true);
                    }
                }

            }
        });

        btnCompletedRaces.addActionListener(new ActionListener() {//showing all completed races button
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame = new JFrame("Completed Races");
                jFrame.setSize(1200,600);
                jFrame.setLayout(null);
                jFrame.getContentPane().setBackground(new Color(35,31,32));

                // Adding label
                JLabel dLabel = new JLabel();
                dLabel.setText("Races Completed");
                dLabel.setBounds(500,50,600,50);
                dLabel.setForeground(Color.RED);
                Object [][] rows = new Object[getDate.size()][11];
                raceDetails(rows);
                String[] column = {"Date","1st Places","2nd Places","3rd Places","4th Places"
                        ,"5th Places","6th Places","7th Places","8th Places","9th Places","10th Places"};
                JTable table = new JTable(rows,column);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(50,180,1100,183);

                jFrame.add(scrollPane);
                jFrame.add(dLabel);
                jFrame.setVisible(true);
            }
        });

        btnRadomRace.addActionListener(new ActionListener() {//random race creating function
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame = new JFrame("Random");
                jFrame.setSize(1200,600);
                jFrame.setLayout(null);
                jFrame.getContentPane().setBackground(new Color(35,31,32));

                // Adding label
                JLabel dLabel = new JLabel();
                dLabel.setText("Random Race");
                dLabel.setBounds(500,50,600,50);
                dLabel.setForeground(Color.RED);
                Object [][] rows = new Object[1][11];
                randomRace(rows);

                String[] column = {"Date","1st Places","2nd Places","3rd Places","4th Places"
                        ,"5th Places","6th Places","7th Places","8th Places","9th Places","10th Places"};
                JTable table = new JTable(rows,column);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(50,180,1100,183);

                jFrame.add(scrollPane);
                jFrame.add(dLabel);
                jFrame.setVisible(true);
            }
        });

    }
    public void setRows(Object[][] rows){//to create the table
        for (int x=0; x < drivers.size(); x++){
            rows[x][0] = drivers.get(x).getName();
            rows[x][1] = drivers.get(x).getManufacturer();
            rows[x][2] = drivers.get(x).getLocation();
            rows[x][3] = drivers.get(x).getFirstPlace();
            rows[x][4] = drivers.get(x).getSecondPlace();
            rows[x][5] = drivers.get(x).getThirdPlace();
            rows[x][6] = drivers.get(x).getFourthPlaces();
            rows[x][7] = drivers.get(x).getFifthPlaces();
            rows[x][8] = drivers.get(x).getSixthPlaces();
            rows[x][9] = drivers.get(x).getSeventhPlaces();
            rows[x][10] = drivers.get(x).getEighthPlaces();
            rows[x][11] = drivers.get(x).getNinthPlaces();
            rows[x][12] = drivers.get(x).getTenthPlace();
            rows[x][13] = drivers.get(x).getAboveTenth();
            rows[x][14] = drivers.get(x).getTotalPoints();
            rows[x][15] = drivers.get(x).getTotalraces();

        }
    }
    public void sortDescending(){//sorting table in descending order when viewing the table(default action)
        for (int x = 0; x < drivers.size();x++){

            for (int i = x+1; i < drivers.size();i++){
                Formula1Driver formulanNew = new Formula1Driver();
                formulanNew = drivers.get(x);
                if (drivers.get(x).getTotalPoints() > drivers.get(i).getTotalPoints()) {
                    drivers.set(x,drivers.get(i));
                    drivers.set(i,formulanNew);
                }else if (drivers.get(x).getTotalPoints() == drivers.get(i).getTotalPoints()){
                    if (drivers.get(x).getFirstPlace() > drivers.get(i).getFirstPlace());
                    drivers.set(x,drivers.get(i));
                    drivers.set(i,formulanNew);
                }
            }
        }
    }


    public void sortAscending(){//sorting table in ascending order
        for (int x = 0; x < drivers.size();x++){

            for (int i = x+1; i< drivers.size();i++){
                Formula1Driver formulanNew = new Formula1Driver();
                formulanNew = drivers.get(x);
                if (drivers.get(x).getTotalPoints() < drivers.get(i).getTotalPoints()) {
                    drivers.set(x,drivers.get(i));
                    drivers.set(i,formulanNew);
                }else if (drivers.get(x).getTotalPoints() == drivers.get(i).getTotalPoints()){
                    if (drivers.get(x).getFirstPlace() < drivers.get(i).getFirstPlace());
                    drivers.set(x,drivers.get(i));
                    drivers.set(i,formulanNew);
                }
            }
        }
    }

    public void sortingFirstPositions(){//sort table according to first positions
        for (int x = 0; x < drivers.size();x++){

            for (int i = x+1; i < drivers.size();i++){
                Formula1Driver newFormula ;
                newFormula = drivers.get(x);
                if (drivers.get(x).getTotalPoints() < drivers.get(i).getTotalPoints()) {
                    drivers.set(x,drivers.get(i));
                    drivers.set(i,newFormula);
                }else if (drivers.get(x).getFirstPlace() == drivers.get(i).getFirstPlace()){
                    if (drivers.get(x).getTotalPoints() < drivers.get(i).getTotalPoints());
                    drivers.set(x,drivers.get(i));
                    drivers.set(i,newFormula);
                }
            }
        }

    }

    public void newSetRows(Object[][] rows,int i){//table creating when search driver is called
        for (int x=0; x < drivers.get(i).getDateDetailArraySize(); x++){
            rows[x][0] = drivers.get(x).getDate(x);
            rows[x][1] = drivers.get(x).getMonth(x);
            rows[x][2] = drivers.get(x).getYear(x);
            rows[x][3] = drivers.get(x).getPosition(x);
        }
    }

    public void raceDetails(Object[][] rows){//creating a table when the function completed races in called
        for (int x=0; x < getDate.size(); x++){
            rows [x][0] = getDate.get(x).dateGen();
            rows [x][1] = getDate.get(x).getFirstPlace();
            rows [x][2] = getDate.get(x).getSecondPlace();
            rows [x][3] = getDate.get(x).getThirdPlace();
            rows [x][4] = getDate.get(x).getFourthPlaces();
            rows [x][5] = getDate.get(x).getFifthPlaces();
            rows [x][6] = getDate.get(x).getSixthPlaces();
            rows [x][7] = getDate.get(x).getSeventhPlaces();
            rows [x][8] = getDate.get(x).getEighthPlaces();
            rows [x][9] = getDate.get(x).getNinthPlaces();
            rows [x][10]= getDate.get(x).getTenthPlace();
        }
    }

    public void randomRace(Object[][] rows){//random race function

        int day = (int) (Math.random() * (32 - 1)) + 1;
        int month = (int) (Math.random() * (13 - 1)) + 1;
        int year = (int) (Math.random() * (2022 - 2015)) + 2015;
        rows[0][0] = day + "-" + month + "-" + year;


        ArrayList<Integer> takenPlaces = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        int place;
        for (int x=0; x < drivers.size();x++){
            do {
                place = (int) (Math.random() * ((drivers.size() + 1) - 1)) + 1;

            }while(takenPlaces.contains(place));
            takenPlaces.add(place);
            drivers.get(x).incrementinOtherPlaces(place);
            names.add(drivers.get(x).getName());
        }

        getDate.add(new SeasonAllDates(day,month, year,takenPlaces,names));
        System.out.println("+----------------Random Race------------------+");
        System.out.println(takenPlaces);
        System.out.println(names);
        System.out.println("+----------------------------------------------+");
        for (int x=0; x < takenPlaces.size(); x++){
            if(takenPlaces.get(x) == 1){
                rows[0][1] = names.get(x);
            }
            else if(takenPlaces.get(x) == 2){
                rows[0][2] = names.get(x);
            }
            else if(takenPlaces.get(x) == 3){
                rows[0][3] = names.get(x);
            }
            else if(takenPlaces.get(x) == 4){
                rows[0][4] = names.get(x);
            }
            else if(takenPlaces.get(x) == 5){
                rows[0][5] = names.get(x);
            }
            else if(takenPlaces.get(x) == 6){
                rows[0][6] = names.get(x);
            }
            else if(takenPlaces.get(x) == 7){
                rows[0][7] = names.get(x);
            }
            else if(takenPlaces.get(x) == 8){
                rows[0][8] = names.get(x);
            }
            else if(takenPlaces.get(x) == 9){
                rows[0][9] = names.get(x);
            }
            else if(takenPlaces.get(x) == 10){
                rows[0][10] = names.get(x);
            }
        }


    }
}