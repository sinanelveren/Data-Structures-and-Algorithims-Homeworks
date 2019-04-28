package com.hotelSinanElveren;

import java.io.FileNotFoundException;

public interface GTUHotelManagementSystem {

    /**
     * get hotel name
     * @return  hotel name
     */
    String getName();

    /**
     *
     * @return hotel capacity
     */
    int getCapacity();

    /**
     *
     * @return hotel ID as long
     */
    long getHotelID();


    /**
     *
     * @return password of recepcionist as string
     */
    String getPassword();

    /**
     *
     * @return recepcionist name
     */
    String getRecepcionist();


    /**
     * Shows all rooms 's status (empty or full)
     */
    void viewBooks();

    /**
     *
     * @return first empty room number, checking it from 1 to end
     */
    int anyEmptyRoom();

    /**
     * senkronize csv file and rooms[]
     * @throws FileNotFoundException .
     */
    void reloadeRooms( ) throws FileNotFoundException;


    /**
     * checkng recepcionist password
     * @param recepcionist recepcionist name
     * @param password recepcionist password
     * @return true if it s correct else false
     */
    boolean checkPaswword(String recepcionist, String password);

}
