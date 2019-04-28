package com.hotelSinanElveren;

import java.io.*;
import java.util.Scanner;


/**
 *  Recepcionist class that inculde recepcionist information.
 */
public class Receptionist extends SystemUsersAbs {

    private String password;
    private static long idCreater = 500;


    /**
     * Constructor get a hotel, a recepcionist name and a recepcionist password.
     * @param HotelS
     * @param name
     * @param password
     * @throws IOException
     */
    public Receptionist(Hotel HotelS, String name, String password) throws IOException {
        super( name);
        setPassword(password);
        createID();
        super.setID(getIDCreater());
        super.writeInfo(HotelS.getHotelID());
    }



    /**
     * Getter for id creater  that is private
     * @return Id creater that is private
     */
    private long getIDCreater() {
        return idCreater;
    }


    /**
     * It initalize and create a id for each recepcionis
     */
    private void createID() {
        idCreater++;
    }


    /**
     * Setter for recepcionist password
     * @param password password of recepcionist
     */
    private void setPassword(String password) {
        this.password = password;
    }


    /**
     * Getter for recepcionist password
     * @return recepcionist 's password that input from user
     */
    public String getPassword() {
        return password;
    }


    /**
     * It checks out if there are any booked room in given room number
     * @param HotelS A hotel
     * @param guestID A guest id
     * @param roomNo A room number
     * @return true if procces has been check out succesfully, else false
     * @throws IOException
     */
    public boolean checkOut(Hotel HotelS, long guestID, int roomNo) throws IOException {

        if(isReserved(HotelS, guestID, roomNo)) {
            super.cancelReservation(HotelS, guestID, roomNo);
            return true;
        }
        else
            return false;
    }

    /**
     * It checks in if there are any booked room in given room number. if there are no reservaation, so make a book
     * @param HotelS A hotel
     * @param guestID A guest id
     * @param roomNo A room number
     * @return true if procces has been check out succesfully, else false
     * @throws IOException
     */
    public boolean checkIn(Hotel HotelS, long guestID, int roomNo) throws IOException {

        if(isReserved(HotelS, guestID, roomNo))
            System.out.println("Checked in");
        else {
            System.out.println("\n\nThere are NOT reservation, so get new record.\n");
            Guest recGuest = new Guest(HotelS, Long.toString(guestID));

            int emptyRoomNum = this.addBooking(HotelS, recGuest);
            if (0 == emptyRoomNum){
                System.out.println("\n\nSorry, Hotel FULL!");
                return false;
            }else {
                this.checkIn(HotelS, recGuest.getID(), emptyRoomNum);
                //if there are emty room, print reservation info
                System.out.println(recGuest.getName() + " has booked a new room succesfully.");
                System.out.println("Reservation info :");
                System.out.println("Guest ID : " + recGuest.getID());
                System.out.println("Room Number : " + emptyRoomNum);
                return true;
            }
        }

        return true;
    }


    /**
     * it checks reservation info if room is booked or not
     * @param HotelS A hotel
     * @param guestID A guest ID
     * @param roomNo  a room number
     * @return true if the guest have reservation in the room, else false
     * @throws IOException
     */
    public boolean isReserved(Hotel HotelS, long guestID, int roomNo) throws IOException {
        File f = new File(HotelS.getHotelID()+"statusOfRooms.csv");
        Scanner sc = new Scanner(f);

        boolean foundReservation = false;
        String inFile="";
        String inLine;

        sc.hasNextLine();
        String temp = sc.nextLine();
        inFile += temp + "\n";
        while(sc.hasNextLine()){
            inLine = sc.nextLine();
            String[] details = inLine.split(",");

            int tempRoomNo = Integer.parseInt(details[3]);
            long tempGuestID =Long.parseLong(details[1]);

            if(tempGuestID == guestID && tempRoomNo == roomNo) {
                foundReservation = true;
                inLine = "";
                inLine += HotelS.getHotelID();
                inLine += ",";
                inLine += guestID;
                inLine += ",";
                inLine += details[2];
                inLine += ",";
                inLine += roomNo;
                inLine += ",";
                inLine += "full";
                inLine += ",";
                inLine += "in";
            }
            inFile += inLine + "\n";

        }

        if(!foundReservation) {
           return false;
        }
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
     * It prints recepcionist info.
     */
    @Override
    public void showUserInfo() {
        System.out.println("User information: - "+ this.getClass().getName() + " class :" );
        System.out.println("It is RECEPCIONIST ");
        System.out.println("ID : " + this.getID());
        System.out.println("Name : " + this.getName());
        System.out.println("password : " + this.getPassword());
    }

    /**
     * To string
     * @return Recepcionist info as string
     */
    @Override
    public String toString() {
        return  "\nSystem user type : " + getClass().getSimpleName()  + "\nName :" + this.getName() +
                "\nPassword : " + this.getPassword() + "\nID :" + this.getID() ;
    }

}
