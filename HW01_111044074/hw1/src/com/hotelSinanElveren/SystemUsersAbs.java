package com.hotelSinanElveren;

import java.io.*;
import java.util.Scanner;

/**
 * SystemUsersAbs for system user that will have 2 sub class Guest and Recepcionist
 */
public abstract class SystemUsersAbs implements SystemUsersInt  {

    private long id;

    private String name;


    /**
     * Constructor get a user name and set it.
     * @param name
     * @throws IOException
     */
    public SystemUsersAbs(String name) throws IOException {

        setName(name);
    }


    /**
     * Setter for user id that guest or recepcionist
     * @param id user id
     */
    protected void setID(long id) {
        this.id = id;
    }


    /**
     * Geter for user id that guest or recepcionist
     * @return user id
     */
    @Override
    public long getID(){
        return id;
    }


    /**
     * Setter for user name
     * @param name user name that recepcionist or guest
     */
    private void setName(String name) {
        this.name = name;
    }


    /**
     * Getter for user name
     * @return user name
     */
    @Override
    public String getName() {
        return this.name;
    }


    /**
     * Wirte users info to data
     * @param hotelID hotel id
     * @throws IOException
     */
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


    /**
     * It will make a booking to system. Need to hotel info and Guest info. T
     * @param HotelS A hotel where you will book
     * @param GuestToRec A Guest who book a room
     * @return room number if reservation has been booked succesfuly, else 0
     * @throws IOException
     */
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


    /**
     * It will cancel reservation from system. Need to Hotel info, Guest id and Room number
     * @param HotelS A hotel where you will cancel booking
     * @param guestID A guest whom will cancel reservation
     * @param roomNo Room number which Guest is residing
     * @return true if reservation has been cancel succesfully, else false
     * @throws IOException
     */
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


    /**
     * It prints user info.
     */
    public void showUserInfo(){
        System.out.println("User information:");
        System.out.println("It is system user");
        System.out.println("No ID");
        System.out.println("No Name");
    }

}
