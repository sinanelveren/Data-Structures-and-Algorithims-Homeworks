package com.hotelSinanElveren;


import java.io.*;


/**
 *  Guest class that inculde guest information.
 */
public class Guest extends SystemUsersAbs {

    protected static long idCreater = 100;

    private int roomNumber = 0;


    /**
     * Constructor get a user name and a hotel..
     * @param HotelS
     * @param guestName
     * @throws IOException
     */
    public Guest(Hotel HotelS, String guestName) throws IOException {

        super(guestName);
        createID();
        super.setID(getIDCreater());
        super.writeInfo(HotelS.getHotelID());
    }


    /**
     * Getter for room number
     * @return room number
     */
    public int getRoomNumber() {
        return roomNumber;
    }


    /**
     * Seter for room number
     * @param roomNumber Room number
     */
    protected void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }


    /**
     * Getter for id creater  that is private
     * @return Id creater that is private
     */
    private long getIDCreater() {
        return idCreater;
    }


    /**
     * It initalize and create a id for each guest
     */
    private void createID() {
        idCreater++;
    }


    /**
     * It prints guest info.
     */
    @Override
    public void showUserInfo() {
        System.out.println("User information:- "+ this.getClass().getName() + " class :" );
        System.out.println("It is GUEST");
        System.out.println("ID : " + this.getID());
        System.out.println("Name : " + this.getName());
        System.out.println("RoomNum : " + this.roomNumber);
    }

    /**
     * To string
     * @return Guest info as string
     */
    @Override
    public String toString() {
        return  "\nSystem user type : " + getClass().getSimpleName()  +
                "\nName :" + this.getName() +"\nID :" + this.getID();
    }

}
