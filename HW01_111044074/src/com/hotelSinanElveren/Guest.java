package com.hotelSinanElveren;


import java.io.*;


public class Guest extends SystemUsersAbs {

    protected static long idCreater = 100;

    private int roomNumber = 0;



    public Guest(Hotel HotelS, String guestName) throws IOException {

        super(guestName);
        createID();
        super.setID(getIDCreater());
        super.writeInfo(HotelS.getHotelID());
    }


    public int getRoomNumber() {
        return roomNumber;
    }


    protected void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }


    private long getIDCreater() {
        return idCreater;
    }


    private void createID() {
        idCreater++;
    }

/*
    @Override
    public int addBooking(Hotel HotelS, Guest GuestToRec) throws IOException {
        setRoomNumber(super.addBooking(HotelS, GuestToRec));

        return this.roomNumber;
    }
*/


        @Override
    public String toString() {
        return  "\nSystem user type : " + getClass().getSimpleName()  +
                "\nName :" + this.getName() +"\nID :" + this.getID();
    }

}
