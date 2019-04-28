package com.hotelSinanElveren;

import java.io.FileNotFoundException;

public interface GTUHotelManagementSystem {

    /**
     * getter hotel name
     * @return  hotel name
     */
    String getName();

    /**
     * getter for capacity
     * @return hotel capacity
     */
    int getCapacity();

    /**
     *Getter for hotel id
     * @return hotel ID as long
     */
    long getHotelID();


    /**
     * Getter for recepcionist default password
     * @return password of recepcionist as string
     */
    String getPassword();

    /**
     * Getter for receocionist default name
     * @return recepcionist name
     */
    String getRecepcionist();


    /**
     * Shows all rooms 's status (empty or full)
     */
    void viewBooks();

    /**
     * It will find a empty room if there are really a empty room.
     * @return Number of first empty room if there are empty room, else return 0.
     */
    int anyEmptyRoom();


    /**
     * It will cause senchronize Hotel's Room and CSV file. Exactly, It copies from file and uptade info of rooms.
     * senkronize csv file and rooms[]
     * @throws FileNotFoundException .
     */
    void reloadeRooms( ) throws FileNotFoundException;


    /**
     * It checks recepcionist's info so compare them password and name.
     * @param recepcionist recepcionist name Recepcionist's name.
     * @param password recepcionist password Recepcionist's password.
     * @return true if recepcionist's login info is coorrect, else false.
     */
    boolean checkPaswword(String recepcionist, String password);

}
