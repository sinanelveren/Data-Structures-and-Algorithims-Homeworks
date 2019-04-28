package Tests;

import com.hotelSinanElveren.Guest;
import com.hotelSinanElveren.Hotel;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class GuestTest {

    /**
     *
     * @throws IOException
     */
    @Test
    public void writeInfo() throws IOException {
        Hotel hotel = new Hotel();
        Guest guest = new Guest(hotel, "guesttestname");

        FileReader file = null;
        file = new FileReader(hotel.getHotelID() + "systemUsers.csv");
        BufferedReader br = new BufferedReader(file);

        String line;

        line = br.readLine();
        line = br.readLine();       //second line, first record
        String[] words = line.split(",");


        //compare user info and new record in line from file
        assertEquals(hotel.getHotelID(), Long.parseLong(words[0]));
        assertEquals(guest.getID(), Long.parseLong(words[1]));
        assertEquals(guest.getName(), words[2]);


        Guest guest2 = new Guest(hotel, "guesttestname2");

        line = br.readLine();       //second line, first record
        words = line.split(",");

        //compare user info and new record in line from file - 2.record
        assertEquals(hotel.getHotelID(), Long.parseLong(words[0]));
        assertEquals(guest2.getID(), Long.parseLong(words[1]));
        assertEquals(guest2.getName(), words[2]);
        br.close();
        file.close();
    }

    @Test
    public void addBooking() throws IOException {
        Hotel hotel = new Hotel();
        Guest guest = new Guest(hotel, "guest1");


        assertEquals(guest.getRoomNumber(), 0 );        //guest have no room

        guest.addBooking(hotel, guest);

        assertNotEquals(guest.getRoomNumber(), 0 );        //guest have no room

        assertEquals(guest.getRoomNumber(), 1 );           //guest is reside at first empty room (it s 1)
    }

    @Test
    public void cancelReservation() throws IOException {
        Hotel hotel = new Hotel();
        Guest guest = new Guest(hotel, "guesttestname");


        long[] rooms = hotel.getRooms();


        guest.addBooking(hotel, guest);

        assertNotEquals(rooms[0], 0 );                 //room 1 have guest, becouse there are guest id
        assertEquals(rooms[0], guest.getID() );

        guest.cancelReservation(hotel,guest.getID(), guest.getRoomNumber());

        assertEquals(rooms[0], 0 );                    //room 1 have no guest, so there are 0
        assertNotEquals(rooms[0], guest.getID() );           //room 1 have no guest, so there are no guest id

    }
}