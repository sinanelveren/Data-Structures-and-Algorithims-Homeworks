package com.hotelSinanElveren;

import java.io.IOException;

public interface SystemUsersInt {

    long getID();

    String getName();

    int addBooking(Hotel HotelS, Guest GuestToRec) throws IOException ;

    boolean cancelReservation(Hotel HotelS, long guestID, int roomNo) throws IOException ;

}
