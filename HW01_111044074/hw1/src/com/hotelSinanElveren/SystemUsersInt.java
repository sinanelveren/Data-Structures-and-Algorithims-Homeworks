package com.hotelSinanElveren;

import java.io.IOException;


/**
 * Interface for system user that will have 2 sub class Guest and Recepcionist
 */
public interface SystemUsersInt {



    /**
     * Getter for user id
     * @return  User id
     * */
    long getID();


    /**
     * Getter for user name
     * @return User name
     */
    String getName();


    /**
     * It will make a booking to system. Need to hotel info and Guest info. T
     * @param HotelS A hotel where you will book
     * @param GuestToRec A Guest who book a room
     * @return room number if reservation has been booked succesfuly, else 0
     * @throws IOException
     */
    int addBooking(Hotel HotelS, Guest GuestToRec) throws IOException ;


    /**
     * It will cancel reservation from system. Need to Hotel info, Guest id and Room number
     * @param HotelS A hotel where you will cancel booking
     * @param guestID A guest whom will cancel reservation
     * @param roomNo Room number which Guest is residing
     * @return true if reservation has been cancel succesfully, else false
     * @throws IOException
     */
    boolean cancelReservation(Hotel HotelS, long guestID, int roomNo) throws IOException ;

    void showUserInfo();

}
