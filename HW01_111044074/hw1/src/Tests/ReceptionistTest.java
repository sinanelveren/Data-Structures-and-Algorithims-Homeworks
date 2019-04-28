package Tests;

import com.hotelSinanElveren.Guest;
import com.hotelSinanElveren.Hotel;
import com.hotelSinanElveren.Receptionist;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class ReceptionistTest {

    /**
     * I addeed a book to system and i checked out it and compare it
     * @throws IOException
     */
    @Test
    public void checkOut() throws IOException {
        Hotel hotel = new Hotel();
        Guest guest = new Guest(hotel, "guesttestname");
        Receptionist receptionist= new Receptionist(hotel, "admin", "admin");


        long[] rooms = hotel.getRooms();


        receptionist.addBooking(hotel, guest);

        assertNotEquals(rooms[0], 0 );                 //room 1 have guest, becouse there are guest id
        assertEquals(rooms[0], guest.getID() );


        receptionist.checkOut(hotel, guest.getID(), guest.getRoomNumber());

        assertEquals(rooms[0], 0 );                    //room 1 have no guest, so there are 0
        assertNotEquals(rooms[0], guest.getID() );           //room 1 have no guest, so there are no guest id
    }

    /**
     * I addeed a book to system and i checked in it and compare it
     * @throws IOException
     */
    @Test
    public void checkIn() throws IOException {
        Hotel hotel = new Hotel();
        Guest guest = new Guest(hotel, "guesttestname");
        Receptionist receptionist= new Receptionist(hotel, "admin", "admin");


        long[] rooms = hotel.getRooms();


        receptionist.addBooking(hotel, guest);

        assertNotEquals(rooms[0], 0 );                 //room 1 have guest, becouse there are guest id
        assertEquals(rooms[0], guest.getID() );


        receptionist.checkIn(hotel, guest.getID(), guest.getRoomNumber());

        assertEquals(rooms[0], guest.getID() );                    //room 1 have no guest, so there are 0
        assertNotEquals(rooms[0], 0 );           //room 1 have no guest, so there are no guest id

    }


    /**
     * I created a user and i checked 2. line from file and i compare it with user
     * @throws IOException
     */
    @Test
    public void writeInfo() throws IOException {
        Hotel hotel = new Hotel();
        Receptionist receptionist = new Receptionist(hotel, "admin", "admin" );


        FileReader file = null;
        file = new FileReader(hotel.getHotelID() + "systemUsers.csv");
        BufferedReader br = new BufferedReader(file);

        String line;

        line = br.readLine();
        line = br.readLine();       //second line, first record
        String[] words = line.split(",");


        //compare user info and new record in line from file
        assertEquals(hotel.getHotelID(), Long.parseLong(words[0]));
        assertEquals(receptionist.getID(), Long.parseLong(words[1]));
        assertEquals(receptionist.getName(), words[2]);

        br.close();
        file.close();
    }


    /**
     * i add ed a book and i copared with old
     * @throws IOException
     */
    @Test
    public void addBooking() throws IOException {
        Hotel hotel = new Hotel();
        Guest guest = new Guest(hotel, "guestname");
        Receptionist receptionist= new Receptionist(hotel, "admin", "admin");


        assertEquals(guest.getRoomNumber(), 0 );        //guest have no room

        receptionist.addBooking(hotel, guest);

        assertNotEquals(guest.getRoomNumber(), 0 );        //guest have no room

        assertEquals(guest.getRoomNumber(), 1 );           //guest is reside at first empty room (it s 1)
    }


    /**
     *
     * I added and canceled a book after that i compare it with old
     * @throws IOException
     */
    @Test
    public void cancelReservation() throws IOException {
        Hotel hotel = new Hotel();
        Guest guest = new Guest(hotel, "guesttestname");
        Receptionist receptionist= new Receptionist(hotel, "admin", "admin");


        long[] rooms = hotel.getRooms();


        receptionist.addBooking(hotel, guest);

        assertNotEquals(rooms[0], 0 );                 //room 1 have guest, becouse there are guest id
        assertEquals(rooms[0], guest.getID() );

        receptionist.cancelReservation(hotel, guest.getID(), guest.getRoomNumber());

        assertEquals(rooms[0], 0 );                    //room 1 have no guest, so there are 0
        assertNotEquals(rooms[0], guest.getID() );           //room 1 have no guest, so there are no guest id

    }


    /**
     * I added book and i read record from file and compared them. if it s reserved or not
     * @throws IOException
     */
    @Test
    public void isReserved() throws IOException {
        Hotel hotel = new Hotel();
        Receptionist receptionist = new Receptionist(hotel, "admin", "admin");
        Guest guest = new Guest(hotel, "guesttestname");


        FileReader file = null;
        file = new FileReader(hotel.getHotelID() + "statusOfRooms.csv");
        BufferedReader br = new BufferedReader(file);

        String line;

        line = br.readLine();
        line = br.readLine();       //second line, first record
        String[] words = line.split(",");


        //compare user info and new record in line from file
        assertEquals(receptionist.isReserved(hotel, guest.getID(), guest.getRoomNumber()), false);  //guest hhave no room

        receptionist.addBooking(hotel, guest);

        assertNotEquals(receptionist.isReserved(hotel, guest.getID(), guest.getRoomNumber()), false);  //guest hhave room as number:1

        br.close();
        file.close();

    }

}