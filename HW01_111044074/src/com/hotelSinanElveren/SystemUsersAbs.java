package com.hotelSinanElveren;

import java.io.*;
import java.util.Scanner;


public abstract class SystemUsersAbs implements SystemUsersInt  {

    private long id;

    private String name;


    public SystemUsersAbs(String name) throws IOException {

        setName(name);
    }


    protected void setID(long id) {
        this.id = id;
    }


    public long getID(){
        return id;
    }


    protected void writeInfo(long hotelID) throws IOException {
        FileWriter fw = new FileWriter(hotelID + "systemUsers.csv", true);

        StringBuilder sb = new StringBuilder();

        sb.append(hotelID);
        sb.append(',');
        sb.append(getID());
        sb.append(',');
        sb.append(getName());

        sb.append('\n');

        fw.write(sb.toString());
        fw.close();
    }


    private void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return this.name;
    }

    @Override
    public int addBooking(Hotel HotelS, Guest GuestToRec) throws IOException {
        FileReader fr = null;

        fr = new FileReader(HotelS.getHotelID() + "statusOfRooms.csv");


        BufferedReader inStream = new BufferedReader(fr);
        String inLine="";
        String inFile="";

        int roomNumber = HotelS.anyEmptyRoom();
        if (roomNumber == 0)
            return roomNumber;      //hotel full

        //set room number where guest reside
        GuestToRec.setRoomNumber(roomNumber);


        //read all file(booking list) line by line, and add new book which reserved
        int i = 0;
        while ( (inLine = inStream.readLine()) != null) {
            inFile += inLine +"\n";

            i++;
            if(i == roomNumber) {
                inLine = "";
                inLine += HotelS.getHotelID();
                inLine += ",";
                inLine += GuestToRec.getID();
                inLine += ",";
                inLine += GuestToRec.getName();
                inLine += ",";
                inLine += roomNumber;
                inLine += ",";
                inLine += "full";
                inLine += ",";
                inLine += "null";
                inLine += "\n";

                inFile += inLine;
                inStream.readLine();        //skip
            }
        }
        inStream.close();


        //reloade file
        FileWriter fw = null;

        fw = new FileWriter(HotelS.getHotelID() + "statusOfRooms.csv", false);


        StringBuilder sb = new StringBuilder();

        sb.append(inFile);

        fw.write(sb.toString());
        fw.close();

        //reloade hotel's room
        HotelS.reloadeRooms();
        return roomNumber;
    }

    @Override
    public boolean cancelReservation(Hotel HotelS, long guestID, int roomNo) throws IOException {
        File f = new File(HotelS.getHotelID()+"statusOfRooms.csv");
        Scanner sc = new Scanner(f);

        boolean foundReservation = false;
        String inFile="";

        sc.hasNextLine();
        String temp = sc.nextLine();
        inFile += temp + "\n";
        while(sc.hasNextLine()){
            String inLine = sc.nextLine();
            String[] details = inLine.split(",");

            int tempRoomNo = Integer.parseInt(details[3]);
            long tempGuestID =Long.parseLong(details[1]);

            if(tempGuestID == guestID && tempRoomNo == roomNo) {
                foundReservation = true;
                inLine = "";
                inLine += HotelS.getHotelID();
                inLine += ",";
                inLine += "0";
                inLine += ",";
                inLine += "null";
                inLine += ",";
                inLine += roomNo;
                inLine += ",";
                inLine += "empty";
                inLine += ",";
                inLine += "null";
            }
            inFile += inLine + "\n";

        }

        if(!foundReservation)
            return false;

        //writenew data to file
        FileWriter fw = new FileWriter(HotelS.getHotelID() + "statusOfRooms.csv", false);

        StringBuilder sb = new StringBuilder();

        sb.append(inFile);

        fw.write(sb.toString());
        fw.close();




        //reloade hotel's room
        HotelS.reloadeRooms();



        return true;

    }
}
